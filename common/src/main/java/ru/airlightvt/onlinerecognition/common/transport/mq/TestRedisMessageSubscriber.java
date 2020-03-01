package ru.airlightvt.onlinerecognition.common.transport.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import ru.airlightvt.onlinerecognition.common.queue.model.QueueMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис, обработки ответных сообщений от ML-модели
 * @author apolyakov
 * @since 23.06.2018
 */
@Service
public class TestRedisMessageSubscriber implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRedisMessageSubscriber.class);

    private RedisTemplate<String, ru.airlightvt.onlinerecognition.common.transport.Message> redisTemplate;
    public static List<String> messageList = new ArrayList<String>();
    public static List<QueueMessage> messages = new ArrayList<>();


    @Autowired
    public TestRedisMessageSubscriber(RedisTemplate<String, ru.airlightvt.onlinerecognition.common.transport.Message> redisTemplate)
    {
        this.redisTemplate =redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        RedisSerializer<?> valueSerializer = redisTemplate.getValueSerializer();
        QueueMessage deserializedMessage = (QueueMessage) valueSerializer.deserialize(message.getBody());
        messageList.add(message.toString());
        messages.add(deserializedMessage);
        System.out.println("Message received: " + deserializedMessage.toString());
        //latch.countDown();
    }
}
