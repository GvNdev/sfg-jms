package com.stmc.sfgjms.listener;

import com.stmc.sfgjms.config.JmsConfig;
import com.stmc.sfgjms.model.HelloWorldMessage;
import jakarta.jms.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by: GvN
 * Date: 08-Feb-26
 * Time: 00:02
 * Project Name: sfg-jms
 */
@Component
public class HelloMessageListener {
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) {
        System.out.println("I Got a Message!!!");

        System.out.println(helloWorldMessage);
    }
}
