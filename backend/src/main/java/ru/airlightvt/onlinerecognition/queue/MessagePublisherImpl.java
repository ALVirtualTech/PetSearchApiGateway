package ru.airlightvt.onlinerecognition.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

/**
 * Реализация {@link MessagePublisher} отправителя сообщений в очереди сообщений
 * @author apolyakov
 * @since 23.06.2018
 */
@Service
public class MessagePublisherImpl implements MessagePublisher{
    private RedisTemplate<String, Object> redisTemplate;
    private ChannelTopic topic;

    public MessagePublisherImpl() {
    }

    @Autowired
    public MessagePublisherImpl(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    @Override
    public void publish(String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
