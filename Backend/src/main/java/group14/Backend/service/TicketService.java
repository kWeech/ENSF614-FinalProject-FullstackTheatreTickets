package group14.Backend.service;

import group14.Backend.model.*;
import group14.Backend.repository.*;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;
    private final ShowtimeRepository showtimeRepository;
    private final SeatRepository seatRepository;
    private final TicketRepository ticketRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final VoucherRepository voucherRepository;
    private final EmailService emailService;
    private final PaymentService paymentService;


    @Autowired
    public TicketService(RegisteredUserRepository registeredUserRepository, MovieRepository movieRepository, TheatreRepository theatreRepository, ShowtimeRepository showtimeRepository, SeatRepository seatRepository, TicketRepository ticketRepository, EmailService emailService, PaymentService paymentService, VoucherRepository voucherRepository) {
        this.registeredUserRepository = registeredUserRepository;
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;
        this.showtimeRepository = showtimeRepository;
        this.seatRepository = seatRepository;
        this.ticketRepository = ticketRepository;
        this.emailService = emailService;
        this.paymentService = paymentService;
        this.voucherRepository = voucherRepository;
    }

    
    /** 
     * Logic for a regular user to pay for a ticket and recieve an email confirmation of their ticket
     * @param seatId
     * @param payment
     */
    public void payAndAddTicket(Long seatId, Payment payment){
        if(!this.paymentService.makePayment(payment)){
            throw new IllegalStateException("Payment invalid");
        }
        Seat seat = this.seatRepository.findSeatById(seatId).get();
        if(seat.isBooked()){
            throw new IllegalStateException("Seat taken!");
        }
        seat.setBooked(true);
        this.seatRepository.save(seat);
        Ticket ticket = new Ticket(seat);
        this.ticketRepository.save(ticket);
        try{
            this.emailService.sendMail(payment.getEmailAddress(), "Purchased Ticket", this.formattedTicketToString(seatId, payment.getEmailAddress()));
        }catch(MessagingException e){
            e.printStackTrace();
        }
    }

    
    /** 
     * Allows for a ticket to be deleted if the requirements are met and will send a voucher for a refund 
     * @param ticketId
     * @param emailAddress
     */
    public void deleteTicket(Long ticketId, String emailAddress) {
        Showtime showtimeOnTicket = this.showtimeRepository.findShowtimeByTicketId(ticketId);
        Seat seatOnTicket = this.seatRepository.findSeatByTicketId(ticketId);
        Optional<Ticket> ticketById = ticketRepository.findById(ticketId);
        if(showtimeOnTicket.getTime().plusHours(72).isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Less than 72 hours before ticket");
        }
        if (ticketById.isPresent()) {
            double refundAmount;
            if(registeredUserRepository.findRegisteredUserIdByTicketId(ticketId) == null){
                refundAmount = seatOnTicket.getPrice() * 0.85;
            }
            else{
                refundAmount = seatOnTicket.getPrice();
            }
            ticketRepository.delete(ticketById.get());
            seatOnTicket.setBooked(false);
            seatRepository.save(seatOnTicket);
            LocalDate expiryDate = LocalDate.now().plus(1, ChronoUnit.YEARS);

            Voucher voucher = new Voucher(expiryDate, refundAmount);
            this.voucherRepository.save(voucher);
            try{
                this.emailService.sendMail(emailAddress, "Purchased Ticket", this.formattedVoucherToString(voucher));
            }catch(MessagingException e){
                e.printStackTrace();
            }
        }else{
            throw new IllegalStateException("Ticket does not exist!");
        }
    }

    
    /** 
     * Logic for a registered user to pay for a ticket and recieve an email confirmation
     * @param userId
     * @param seatId
     */
    public void registeredUserPayAndAddTicket(Long userId, Long seatId) {
        Optional<RegisteredUser> registeredUserById = registeredUserRepository.findRegisteredUserById(userId);
        if (registeredUserById.isEmpty()) {
            throw new IllegalStateException("User doesn't exist!");
        }
        Optional<Seat> seatById = seatRepository.findSeatById(seatId);
        if (seatById.isEmpty()) {
            throw new IllegalStateException("Seat doesn't exist!");
        }
        RegisteredUser registeredUser = registeredUserById.get();
        Seat seat = seatById.get();
        if(seat.isBooked()){
            throw new IllegalStateException("Seat taken!");
        }

        //Testing over 10% booking for prepurchasing
        Showtime showtimeOnTicket = showtimeRepository.findShowtimeBySeatId(seatId);
        Movie movieOnTicket = movieRepository.findMovieBySeatId(seatId);
        int booked_count = 0;
        List<Seat> seatList = new ArrayList<>(showtimeOnTicket.getSeats());
        for (Seat value : seatList) {
            if (value.isBooked()) {
                booked_count++;
            }
        }
        if(movieOnTicket.getPreReleaseDate().isBefore(LocalDate.now()) &&
                movieOnTicket.getReleaseDate().isAfter(LocalDate.now()) &&
                booked_count >= showtimeOnTicket.getSeats().size()/10){
            throw new IllegalStateException("10% of prepurchase seats already booked. Wait for release date to book!");
        }

        seat.setBooked(true);
        this.seatRepository.save(seat);
        Ticket ticket = new Ticket(seat);
        registeredUser.addTicket(ticket);
        this.ticketRepository.save(ticket);
        this.registeredUserRepository.save(registeredUser);
        try{
            this.emailService.sendMail(registeredUser.getPaymentInfo().getEmailAddress(), "Purchased Ticket", this.formattedTicketToString(seatId, registeredUser.getPaymentInfo().getEmailAddress()));
        }catch(MessagingException e){
            e.printStackTrace();
        }
    }

    
    /** 
     * Formats the ticket to a string to be sent to the users in an email
     * @param seatId
     * @param emailAddress
     * @return String
     */
    public String formattedTicketToString(Long seatId, String emailAddress){
        Ticket ticket = this.ticketRepository.findTicketBySeatId(seatId);
        Seat seatOnTicket = seatRepository.findById(seatId).get();
        Showtime showtimeOnTicket = showtimeRepository.findShowtimeBySeatId(seatId);
        Theatre theatreOnTicket = theatreRepository.findTheatreBySeatId(seatId);
        Movie movieOnTicket = movieRepository.findMovieBySeatId(seatId);

        String sb = "Ticket Summary" +
                "\nMovie: " + movieOnTicket.getTitle() +
                "\nTheatre: " + theatreOnTicket.getName() +
                "\nShowtime: " + DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd").format(showtimeOnTicket.getTime()) +
                "\nSeat: " + seatOnTicket.getRowLetter() + "-" + seatOnTicket.getSeatNum() +
                "\nPrice: $" + seatOnTicket.getPrice() + "\n" + "\n" +
                "To cancel ticket, click the following link" +
                "\nhttp://localhost:8080/ticket/delete/" + ticket.getId() + "/" + emailAddress;
        return sb;
    }

    
    /** 
     * Formats the voucher as a string to be sent to the user in an email
     * @param voucher
     * @return String
     */
    public String formattedVoucherToString(Voucher voucher) {
        String sb = "Your ticket has been successfully cancelled. The summary of your voucher is as follows." +
                "\nVoucher Id: " + voucher.getId() +
                "\nVoucher Amount: $" + voucher.getVoucherAmount() +
                "\nVoucher Valid Until:  " + voucher.getExpiryDate() +
                "\nTo use your voucher, simply enter in the voucher id at checkout prior to the expiry date.";
        return sb;
    }
}
