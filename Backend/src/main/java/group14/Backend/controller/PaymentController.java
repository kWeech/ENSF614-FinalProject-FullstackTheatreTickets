package group14.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group14.Backend.model.Payment;
import group14.Backend.service.PaymentService;

@RestController
@RequestMapping("/payment")
@CrossOrigin
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    
    /** 
     * Adds a new payment object to the database via POST /payment. body is payment object
     * @param payment
     * @return Payment
     */
    @PostMapping
    public Payment addPayment(@RequestBody Payment payment) {
        return paymentService.addNewPayment(payment);
    }
    
    
    /** 
     * Updates users payment via PUT payment/{userId}. body is payment object 
     * @param userId
     * @param payment
     */
    @PutMapping("/{userId}")
    public void updatePayment(@PathVariable("userId") Long userId, @RequestBody Payment payment){
        paymentService.updatePayment(userId, payment);
    }

}
