package ru.airlightvt.onlinerecognition.common.transport.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import ru.airlightvt.onlinerecognition.common.transport.Message;
import ru.airlightvt.onlinerecognition.common.transport.Transport;

import java.net.URI;

@Component
@ConditionalOnProperty(value="transport.type", havingValue = "redis")
@Slf4j
public class RedisTransport implements Transport {
    private RedisTemplate<String, Message> redisTemplate;

    @Override
    public void send(Message message, URI uri) {
        log.info("Sending message {} to channel {}", message, uri.getHost());
        redisTemplate.convertAndSend(uri.getHost(), message);
    }
}
