package com.volkova.kandalov.springlab2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.notify.from}")
    private String from;

    @Value("${app.notify.recipients}")
    private String recipients;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String subject, String text) {
        System.out.println("EMAIL SENT (FAKE): subject=" + subject + " text=" + text);

        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(from);
            msg.setTo(parseRecipients());
            msg.setSubject(subject);
            msg.setText(text);
            mailSender.send(msg);
        } catch (Exception e) {
            System.out.println("SMTP not available, message not actually sent (OK for local test): " + e.getMessage());
        }
    }

    private String[] parseRecipients() {
        return Arrays.stream(recipients.split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toArray(String[]::new);
    }
}
