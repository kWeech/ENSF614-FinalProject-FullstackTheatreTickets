package group14.Backend.service;

import group14.Backend.model.Payment;
import group14.Backend.model.RegisteredUser;
import group14.Backend.repository.PaymentRepository;
import group14.Backend.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RegisteredUserRepository registeredUserRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, RegisteredUserRepository registeredUserRepository){
        this.paymentRepository = paymentRepository;
        this.registeredUserRepository = registeredUserRepository;
    }

    
    /** 
     * Takes a peyment object to add to the database
     * @param payment
     * @return Payment
     */
    public Payment addNewPayment(Payment payment) {
        Optional<Payment> optionalPaymentByCardNumber = paymentRepository.findPaymentByCardNumber(payment.getCardNumber());
        if (optionalPaymentByCardNumber.isPresent()) {
            return optionalPaymentByCardNumber.get();
        }else{
            paymentRepository.save(payment);
            return payment;
        }
    }

    
    /** 
     * Mimics a check that would be completed by a financial institute
     * @param payment
     * @return boolean
     */
    public boolean makePayment(Payment payment){
        if(payment.getCardNumber().length() != 16){
            throw new IllegalStateException("Card number invalid");
        } else if(payment.getCardHolderName().equals("")){
            throw new IllegalStateException("No fields can be empty");
        } else if(payment.getCardNumber().equals("")){
            throw new IllegalStateException("No fields can be empty");
        } else if (payment.getExpiryDate() == null) {
            throw new IllegalStateException("No fields can be empty");
        } else if (payment.getCvc() == 0) {
            throw new IllegalStateException("No fields can be empty");
        } else if (payment.getEmailAddress().equals("")) {
            throw new IllegalStateException("No fields can be empty");
        } else if(payment.getAddress().equals("")){
            throw new IllegalStateException("No fields can be empty");
        } else if (payment.getCity().equals("")) {
            throw new IllegalStateException("No fields can be empty");
        } else if (payment.getProvince().equals("")) {
            throw new IllegalStateException("No fields can be empty");
        } else if (payment.getPostalCode().equals("")) {
            throw new IllegalStateException("No fields can be empty");
        }
        return true;
    }

    
    /** 
     * Takes a user id and payment object to update the payment method registered to a registered user
     * @param userId
     * @param payment
     */
    public void updatePayment(Long userId, Payment payment) {
        if(payment.getCardHolderName().equals("")){
            throw new IllegalStateException("No fields can be empty");
        } else if(payment.getCardNumber().equals("")){
            throw new IllegalStateException("No fields can be empty");
        } else if (payment.getExpiryDate() == null) {
            throw new IllegalStateException("No fields can be empty");
        } else if (payment.getCvc() == 0) {
            throw new IllegalStateException("No fields can be empty");
        } else if (payment.getEmailAddress().equals("")) {
            throw new IllegalStateException("No fields can be empty");
        } else if(payment.getAddress().equals("")){
            throw new IllegalStateException("No fields can be empty");
        } else if (payment.getCity().equals("")) {
            throw new IllegalStateException("No fields can be empty");
        } else if (payment.getProvince().equals("")) {
            throw new IllegalStateException("No fields can be empty");
        } else if (payment.getPostalCode().equals("")) {
            throw new IllegalStateException("No fields can be empty");
        }
        Optional<RegisteredUser> registeredUserOptional = registeredUserRepository.findRegisteredUserById(userId);
        if(!registeredUserOptional.isPresent()){
            throw new IllegalStateException("User does not exist");
        }
        RegisteredUser registeredUser = registeredUserOptional.get();
        Payment paymentToDelete = registeredUser.getPaymentInfo();
        paymentRepository.save(payment);
        registeredUser.setPaymentInfo(payment);
        if(!(paymentToDelete == null)){
            paymentRepository.delete(paymentToDelete);
        }
        registeredUserRepository.save(registeredUser);
    }
}
