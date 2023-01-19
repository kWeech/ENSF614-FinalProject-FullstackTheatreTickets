package group14.Backend.controller;

import group14.Backend.model.RegisteredUser;
import group14.Backend.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registereduser")
@CrossOrigin
public class RegisteredUserController {

    private final RegisteredUserService registeredUserService;

    @Autowired
    public RegisteredUserController(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

    
    /** 
     * adds new registered user via POST /registereduser/register. body is registeredUser
     * @param registeredUser
     */
    @PostMapping("/register")
    public void registerNewRegisteredUser(@RequestBody RegisteredUser registeredUser){
        registeredUserService.addNewRegisteredUser(registeredUser);
    }

    
    /** 
     * logs in user via POST. body is registeredUser.
     * @param registeredUser
     * @return RegisteredUser
     */
    @PostMapping("/login")
    public RegisteredUser loginRegisteredUser(@RequestBody RegisteredUser registeredUser) {
        return registeredUserService.loginRegisteredUser(registeredUser);
    }

    
    /** 
     * registered user pays subscription /registereduser/paySubscription/{userId}. Uses stored payment
     * @param userId
     */
    @PutMapping("/paySubscription/{userId}")
    public void paySubscription(@PathVariable("userId") Long userId) {
        registeredUserService.paySubscription(userId);
    }

    /**
     *  sends registered users email on the movie news.
     */
    @GetMapping("/sendemailnews")
    public void sendEmailNews(){
        registeredUserService.sendEmailNews();
    }

}
