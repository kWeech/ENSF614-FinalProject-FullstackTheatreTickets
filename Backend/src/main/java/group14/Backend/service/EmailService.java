package group14.Backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    
    /** 
     * Sets attributes of email being sent
     * @param toEmail
     * @param subject
     * @param message
     * @throws MessagingException
     */
    public void sendMailMultipart(String toEmail, String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(message);
        mailSender.send(mimeMessage);
    }

    
    /** 
     * Helper function for sending an email
     * @param toEmail
     * @param subject
     * @param message
     * @throws MessagingException
     */
    public void sendMail(String toEmail, String subject, String message) throws MessagingException {
        sendMailMultipart(toEmail, subject, message);
    }
}
