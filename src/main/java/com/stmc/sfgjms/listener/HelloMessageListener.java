package com.stmc.sfgjms.listener;

import com.stmc.sfgjms.config.JmsConfig;
import com.stmc.sfgjms.model.HelloWorldMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by: GvN
 * Date: 08-Feb-26
 * Time: 00:02
 * Project Name: sfg-jms
 */
@RequiredArgsConstructor
@Component
public class HelloMessageListener {
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_SEND_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) {
    }

    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) throws JMSException {
        HelloWorldMessage payloadMsg = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("World!!!")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMsg);
    }
}
