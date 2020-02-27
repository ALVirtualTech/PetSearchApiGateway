package ru.airlightvt.onlinerecognition.common.queue;

import ru.airlightvt.onlinerecognition.common.queue.model.QueueMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
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
    private RedisTemplate<String, Object> redisTemplate;
    public static List<String> messageList = new ArrayList<String>();
    public static List<QueueMessage> messages = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSubscriber.class);

    private CountDownLatch latch;

    @Autowired
    public MessageSubscriber(CountDownLatch latch, @Qualifier("redisJacksonTemplate") RedisTemplate<String, Object> redisTemplate)
    {
        this.latch = latch;
        this.redisTemplate =redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        RedisSerializer<?> valueSerializer = redisTemplate.getValueSerializer();
        QueueMessage deserializedMessage = (QueueMessage) valueSerializer.deserialize(message.getBody());
        messageList.add(message.toString());
        messages.add(deserializedMessage);
        System.out.println("Message received: " + new String(deserializedMessage.toString()));
        //latch.countDown();
    }
}
