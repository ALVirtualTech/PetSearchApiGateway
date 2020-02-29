package ru.airlightvt.onlinerecognition.common.transport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import ru.airlightvt.onlinerecognition.common.transport.mq.Message;

import java.net.URI;

@Component
@Slf4j
public class RedisTransport implements Transport {
    private RedisTemplate<String, Message> redisTemplate;

    @Override
    public void send(Message message, URI uri) {
        log.info("Sending message {} to channel {}", message, uri.getHost());
        redisTemplate.convertAndSend(uri.getHost(), message);
    }
}
