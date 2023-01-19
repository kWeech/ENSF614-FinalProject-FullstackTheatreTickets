package group14.Backend.service;

import group14.Backend.model.RegisteredUser;
import group14.Backend.model.Movie;
import group14.Backend.repository.RegisteredUserRepository;
import group14.Backend.repository.MovieRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RegisteredUserService {
    private final RegisteredUserRepository registeredUserRepository;
    private final MovieRepository movieRepository;
    private final PaymentService paymentService;
    private final EmailService emailService;

    @Autowired
    public RegisteredUserService(RegisteredUserRepository registeredUserRepository, MovieRepository movieRepository, PaymentService paymentService, EmailService emailService){
        this.registeredUserRepository = registeredUserRepository;
        this.movieRepository = movieRepository;
        this.paymentService = paymentService;
        this.emailService = emailService;
    }

    
    /** 
     * Takes in a user object to add to the database
     * @param registeredUser
     */
    public void addNewRegisteredUser(RegisteredUser registeredUser) {
        Optional<RegisteredUser> registeredUserByUsername = registeredUserRepository.findRegisteredUserByUsername(registeredUser.getUsername());
        if (registeredUserByUsername.isPresent()) {
            System.out.println(registeredUserByUsername);
            throw new IllegalStateException("Username taken!");
        }
        registeredUserRepository.save(registeredUser);
    }

    
    /** 
     * verifies that a user trying to login exists and if they do that user object is returned
     * @param registeredUser
     * @return RegisteredUser
     */
    public RegisteredUser loginRegisteredUser(RegisteredUser registeredUser) {
        Optional<RegisteredUser> registeredUserByUsername = registeredUserRepository.findRegisteredUserByUsername(registeredUser.getUsername());
        if (!registeredUserByUsername.isPresent()) {
            throw new IllegalStateException("User doesn't exist!");
        }
        RegisteredUser userQueryResult = registeredUserByUsername.get();
        String password = userQueryResult.getPassword();
        String username = userQueryResult.getUsername();
        if (!password.equals(registeredUser.getPassword()) || !username.equals(registeredUser.getUsername())) {
            throw new IllegalStateException("Username or password is incorrect!");
        }
        return userQueryResult;
    }

    
    /** 
     * Allows a registered user to pay a subscription to give them one more year of perks
     * @param userId
     */
    public void paySubscription(Long userId) {
        Optional<RegisteredUser> registeredUserById = registeredUserRepository.findRegisteredUserById(userId);
        if (!registeredUserById.isPresent()) {
            throw new IllegalStateException("User doesn't exist!");
        }
        RegisteredUser registeredUser = registeredUserById.get();
        if(!paymentService.makePayment(registeredUser.getPaymentInfo())){
            throw new IllegalStateException("Payment declined!");
        }
        registeredUser.setSubscriptionEndDate(registeredUser.getSubscriptionEndDate().plusYears(1));
        registeredUser.setSubscriptionPaymentDate(LocalDate.now());
        registeredUserRepository.save(registeredUser);
    }

    /**
     * Sends news to the registered users about upcoming movies
     */
    public void sendEmailNews(){
        LocalDate currentDate = LocalDate.now();
        List<RegisteredUser> allRegisteredUsers = this.registeredUserRepository.findAll();
        List<Movie> notPreReleased = this.movieRepository.findAllNotPreReleasedMovies(currentDate);
        List<Movie> availableForPreOrder = this.movieRepository.findAllAvailableForPreOrderMovies(currentDate);
        List<Movie> publiclyAvailable = this.movieRepository.findAllPubliclyAvailableMovies(currentDate);
        for(RegisteredUser ru : allRegisteredUsers){
            try{
                this.emailService.sendMail(ru.getPaymentInfo().getEmailAddress(), "Movie News", this.formattedEmailNewsToString(ru, notPreReleased, availableForPreOrder, publiclyAvailable));
            }catch(MessagingException e){
                e.printStackTrace();
            }
        }
    }

    
    /** 
     * Formats the email news being sent to the user into a string
     * @param ru
     * @param notPreReleased
     * @param availableForPreOrder
     * @param publiclyAvailable
     * @return String
     */
    public String formattedEmailNewsToString(RegisteredUser ru, List<Movie> notPreReleased,List<Movie> availableForPreOrder,List<Movie> publiclyAvailable){
        StringBuilder sb = new StringBuilder();
        sb.append("Hello " + ru.getPaymentInfo().getCardHolderName() + "!");

        if (!notPreReleased.isEmpty()){
            sb.append("\nMovies Available For PreOrder Soon");
            for(Movie m : notPreReleased){
                sb.append("\n" + m.getTitle() + " is available for preorder on " + m.getPreReleaseDate());
            }
        }
        sb.append("\n\n");

        if(!availableForPreOrder.isEmpty()){
            sb.append("\nMovies Now Available For PreOrder");
            for(Movie m : availableForPreOrder){
                sb.append("\n" + m.getTitle() + " is now available for preorder until 10% of the seats are taken by other registered users");
            }
        }
        sb.append("\n\n");

        if(!publiclyAvailable.isEmpty()){
            sb.append("\nMovies Now Publicly Available");
            for(Movie m : publiclyAvailable){
                sb.append("\n" + m.getTitle() + " is publicly available until no longer in theatres");
            }
        }
        return sb.toString();
    }
}
