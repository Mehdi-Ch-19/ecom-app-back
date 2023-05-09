package com.ecomapp.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jdk.jfr.ContentType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static org.springframework.amqp.core.MessageProperties.CONTENT_TYPE_JSON;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducer {

    private final AmqpTemplate amqpTemplate;
    private ObjectMapper objectMapper;

    public void publish(Object payload , String exchange,String routingKey) throws JsonProcessingException {
        try {
            log.info("Publishing too {} using routingKey {}. Payload: {}", exchange, routingKey, payload.toString());
            objectMapper.registerModule(new JavaTimeModule());
            String objJson = objectMapper.writeValueAsString(payload);
            Message message = MessageBuilder.withBody(objJson.getBytes())
                    .setContentType(CONTENT_TYPE_JSON).build();
            amqpTemplate.convertAndSend(exchange, routingKey, message);

            log.info("Published to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
        }catch (Exception e){
            throw e;
        }

    }

}
