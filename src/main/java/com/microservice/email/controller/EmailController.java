package com.microservice.email.controller;

import com.microservice.email.entity.EmailEntity;
import com.microservice.email.request.EmailRequest;
import com.microservice.email.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/sending-email")
    public ResponseEntity<EmailEntity> sendEmail(@RequestBody @Valid EmailRequest emailRequest) {

        EmailEntity emailEntity = new EmailEntity();

        BeanUtils.copyProperties(emailRequest, emailEntity);

        emailService.sendEmail(emailEntity);

        return new ResponseEntity<>(emailEntity, HttpStatus.CREATED);
    }
}
