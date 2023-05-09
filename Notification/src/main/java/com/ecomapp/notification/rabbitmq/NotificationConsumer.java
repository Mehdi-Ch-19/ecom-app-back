package com.ecomapp.notification.rabbitmq;

import com.ecomapp.feign.notification.NotificationRequest;
import com.ecomapp.notification.service.EmailSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class NotificationConsumer {
    //static final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);
    private final EmailSender emailSender;

    public NotificationConsumer(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @RabbitListener(queues = "notification.queue")
    public void processOrder(Message message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        NotificationRequest notificationRequest = objectMapper.readValue(message.getBody(), NotificationRequest.class);
        try {
          emailSender.sendOrderConfirmationEmail(notificationRequest);
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }
}
