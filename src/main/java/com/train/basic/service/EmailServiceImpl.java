package com.train.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.train.basic.model.Role;
import com.train.basic.model.User;
import com.train.basic.repository.UserRepository;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void sendEmail(String to, String subject, String body) {
        try {
           SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            System.out.println("Email sent successfully to " + to);
        } catch (Exception e) {
            System.err.println("Failed to send email to " + to + ": " + e.getMessage());
        }
    }


    @Async
    @Override
    public void sendEmailToAllCustomers(String subject, String body) {
        List<User> customers = userRepository.findByRole(Role.CUSTOMER);
        customers.forEach(customer -> sendEmail(customer.getEmail(), subject, body));
    }

}