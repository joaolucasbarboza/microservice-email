package com.microservice.email.service;

import com.microservice.email.entity.EmailEntity;
import com.microservice.email.enums.StatusEmail;
import com.microservice.email.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender mailSender;

    public EmailEntity sendEmail(EmailEntity emailEntity) {

        emailEntity.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(emailEntity.getEmailFrom());
            message.setTo(emailEntity.getEmailTo());
            message.setSubject(emailEntity.getSubject());
            message.setText(emailEntity.getBody());
            mailSender.send(message);

            emailEntity.setStatus(StatusEmail.SENT);
        } catch (MailException e) {
            emailEntity.setStatus(StatusEmail.ERROR);
        } finally {
           return emailRepository.save(emailEntity);
        }
    }
}
