package ru.airlightvt.onlinerecognition.common.transport.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.airlightvt.onlinerecognition.common.queue.model.QueueMessage;
import ru.airlightvt.onlinerecognition.common.transport.TransportConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис, обработки ответных сообщений от ML-модели
 * @author apolyakov
 * @since 23.06.2018
 */
@Component
@ConditionalOnProperty(value="transport.type", havingValue = "redis")
@Slf4j
@RequiredArgsConstructor
public class TestRedisMessageSubscriber implements MessageListener {
    private final RedisTemplate<String, ru.airlightvt.onlinerecognition.common.transport.Message> redisTemplate;

    public static List<String> messageList = new ArrayList<String>();
    public static List<QueueMessage> messages = new ArrayList<>();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        RedisSerializer<?> valueSerializer = redisTemplate.getValueSerializer();
        QueueMessage deserializedMessage = (QueueMessage) valueSerializer.deserialize(message.getBody());
        messageList.add(message.toString());
        messages.add(deserializedMessage);
        System.out.println("Message received: " + deserializedMessage.toString());
    }
}
