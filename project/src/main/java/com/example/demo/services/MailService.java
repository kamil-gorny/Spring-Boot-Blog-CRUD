package com.example.demo.services;

import javax.mail.MessagingException;

public interface MailService {
    void sendMail(String to, String subject, String text, boolean isHtmlContent)throws MessagingException;
}
