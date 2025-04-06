package com.example.emailverification.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for sending verification emails to users.
 * This class handles the email composition and delivery process.
 */
@Service // Marks this class as a Spring service component for dependency injection
public class EmailService {

    // Email sender interface from Spring for handling mail operations
    private final JavaMailSender mailSender;

    /**
     * Constructor-based dependency injection for mail sender
     * 
     * @param mailSender The JavaMailSender implementation for sending emails
     */
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends a verification email containing a confirmation token to the user
     * The @Async annotation allows this method to execute in a separate thread,
     * preventing email sending from blocking the main application flow
     * 
     * @param to The recipient's email address
     * @param token The verification token to include in the email
     * @throws IllegalIdentifierException if email sending fails
     */
    @Async
    public void sendSimpleMail(String to, String token) {
        try {
            // Create a simple mail message object
            SimpleMailMessage message = new SimpleMailMessage();

            // Set email properties
            message.setTo(to);                              // Recipient
            message.setFrom("sadisumohammed27@gmail.com");  // Sender
            message.setSubject("Confirm your email");       // Subject
            
            // Construct email body with verification link using text block and string formatting
            String body = """

            

                    Hello from Awesome App Team!
                    Please use the following link to verify your email:

                    http://localhost:8080/register/confirmToken?token=%s
                    """.formatted(token);
            message.setText(body);
            
            // Send the email
            mailSender.send(message);
        } catch (Exception e) {
            // Log the exception and rethrow with a more specific message
            e.printStackTrace();
            throw new IllegalIdentifierException("Failed to send email");
        }
    }
}