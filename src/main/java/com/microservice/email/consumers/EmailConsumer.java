package com.microservice.email.consumers;

import com.microservice.email.entity.EmailEntity;
import com.microservice.email.request.EmailRequest;
import com.microservice.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "ms-email")
    public void listen(@Payload EmailRequest emailRequest) {

        EmailEntity emailEntity = new EmailEntity();

        BeanUtils.copyProperties(emailRequest, emailEntity);

        emailService.sendEmail(emailEntity);

        System.out.println("Email sent: " + emailEntity.getStatus().toString());
    }
}
