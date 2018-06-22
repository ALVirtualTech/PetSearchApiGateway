package ru.airlightvt.onlinerecognition.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import ru.airlightvt.onlinerecognition.queue.MessagePublisher;
import ru.airlightvt.onlinerecognition.queue.MessagePublisherImpl;
import ru.airlightvt.onlinerecognition.queue.MessageSubscriber;

import javax.inject.Named;
import java.util.concurrent.CountDownLatch;

/**
 * Конфигурация распределенного кэша-очереди сообщений Redis
 * @author apolyakov
 * @since 23.06.2018
 */
@Configuration
@ComponentScan("ru.airlightvt.onlinerecognition")
public class RedisConfig {
    /**
     * Фабрика для конструирования соединия с redis
     * @return JedisConnectionFactory
     */
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
        jedisConFactory.setHostName("192.168.99.100");
        jedisConFactory.setPort(32772);
        return jedisConFactory;
    }

    /**
     * Шаблон записей для помещения в очередь сообщений redis
     * key: String, value: Serializable to String object
     * @return RedisTemplate<String, Object>
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }

    /**
     * Отправитель сообщений в очередь redis
     * @return MessagePublisher
     */
    @Named(value = "redisPublisher")
    @Bean
    MessagePublisher redisPublisher() {
        return new MessagePublisherImpl(redisTemplate(), topic());
    }

    /**
     * Получатель сообщений из очереди redis
     * defaultListenerMethod = MessageSubscriber#onMessage
     * @return MessageListenerAdapter
     */
    @Bean
    MessageListenerAdapter messageListener(CountDownLatch latch) {
        return new MessageListenerAdapter(new MessageSubscriber(latch));
    }

    /**
     * Контейнер слушателей сообщений из очередей redis
     * @return
     */
    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(latch()), topic());
        return container;
    }

    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(1);
    }

    /**
     * Название очереди (одной и конкретной!) на отправку сообщений с данными авторизации
     * @return ChannelTopic
     */
    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("pubsub:queue");
    }
}
