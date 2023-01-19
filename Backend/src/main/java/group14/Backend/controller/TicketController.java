package group14.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group14.Backend.model.Payment;
import group14.Backend.service.TicketService;

@RestController
@RequestMapping("/ticket")
@CrossOrigin
public class TicketController {

    private final TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    
    /** 
     * guest user buys ticket via POST /ticket/add/{seatId} where body is payment object
     * @param seatId
     * @param payment
     */
    @PostMapping("/add/{seatId}")
    public void payAndAddTicket(@PathVariable("seatId") Long seatId, @RequestBody Payment payment) {
        this.ticketService.payAndAddTicket(seatId, payment);
    }

    
    /** 
     * registered user buys ticket via POST /ticket/add/{userId}/{seatId}. Uses stored payment object from registered user
     * @param userId
     * @param seatId
     */
    @PostMapping("/add/{userId}/{seatId}")
    public void registeredUserPayAndAddTicket(@PathVariable("userId") Long userId, @PathVariable("seatId") Long seatId) {
        this.ticketService.registeredUserPayAndAddTicket(userId, seatId);
    }

    
    /** 
     * registered user cancels ticket via DELETE /ticket/delete/{ticketId}/{emailAddress}
     * @param ticketId
     * @param emailAddress
     */
    @GetMapping("/delete/{ticketId}/{emailAddress}")
    public void deleteTicket(@PathVariable("ticketId") Long ticketId, @PathVariable("emailAddress") String emailAddress) {
        this.ticketService.deleteTicket(ticketId, emailAddress);
    }

}