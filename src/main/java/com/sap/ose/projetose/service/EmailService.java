package com.sap.ose.projetose.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Value("${sendEmails}")
    private Boolean enabled = false;

    private final JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        if (!enabled) {
            return;
        }
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("notifications@ose.vaneixus.com");

        mailSender.send(message);
    }
}