package com.stmc.sfgjms.sender;

import com.stmc.sfgjms.config.JmsConfig;
import com.stmc.sfgjms.model.HelloWorldMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

/**
 * Created by: GvN
 * Date: 07-Feb-26
 * Time: 23:30
 * Project Name: sfg-jms
 */
@RequiredArgsConstructor
@Component
public class HelloSender {
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_SEND_QUEUE, message);

        System.out.println("Message sent");
    }

    @Scheduled(fixedRate = 2000)
    public void sendAndReceiveMessage() throws JMSException {
        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello")
                .build();

        Message receivedMsg = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                helloMessage.setStringProperty("_type", "com.stmc.sfgjms.model.HelloWorldMessage");
                System.out.println("Sending Hello");
                return helloMessage;
            }
        });

        System.out.println(receivedMsg.getBody(String.class));
    }
}
