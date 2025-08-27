package com.train.basic.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
    void sendEmailToAllCustomers(String subject, String body);
}

