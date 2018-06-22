package ru.airlightvt.onlinerecognition.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Сервис, обработки ответных сообщений от ML-модели
 * @author apolyakov
 * @since 23.06.2018
 */
@Service
public class MessageSubscriber implements MessageListener {
    public static List<String> messageList = new ArrayList<String>();

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSubscriber.class);

    private CountDownLatch latch;

    @Autowired
    public MessageSubscriber(CountDownLatch latch)
    {
        this.latch = latch;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        messageList.add(message.toString());
        System.out.println("Message received: " + new String(message.getBody()));
        //latch.countDown();
    }
}
