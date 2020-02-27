package ru.airlightvt.onlinerecognition.common.queue;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ru.airlightvt.onlinerecognition.common.queue.model.QueueMessage;
import ru.airlightvt.onlinerecognition.common.queue.model.QueueMessageType;

import java.util.concurrent.CountDownLatch;

/**
 * Конфигурация распределенного кэша-очереди сообщений Redis
 * @author apolyakov
 * @since 23.06.2018
 */
@Configuration
//@ComponentScan("ru.airlightvt.onlinerecognition.common.queue")
public class RedisConfig {
    /**
     * Фабрика для конструирования соединия с redis
     * @return JedisConnectionFactory
     */
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    /**
     * Шаблон записей для помещения в очередь сообщений redis
     * key: String, value: Serializable to String object
     * @return RedisTemplate<String, Object>
     */
    @Bean(name="redisGenericTemplate")
    public RedisTemplate<String, QueueMessage> redisGenericTemplate() {
        final RedisTemplate<String, QueueMessage> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<>(QueueMessage.class));
        return template;
    }

    /**
     * Отправитель сообщений в очередь redis
     * @return MessagePublisher
     */
    @Qualifier("redisPublisher")
    @Bean
    MessagePublisher redisPublisher() {
        return new MessagePublisherImpl(redisJacksonTemplate(jedisConnectionFactory()), topicArticleUpdateReq());
    }

    /**
     * Получатель сообщений из очереди redis
     * defaultListenerMethod = MessageSubscriber#onMessage
     * @return MessageListenerAdapter
     */
    @Bean
    MessageListenerAdapter messageListener(CountDownLatch latch) {
        return new MessageListenerAdapter(new MessageSubscriber(latch, redisJacksonTemplate(jedisConnectionFactory())));
    }

    /**
     * Контейнер слушателей сообщений из очередей redis
     * TODO поменять канал в очереди redis
     * @return
     */
    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(latch()), topicArticleUpdateReq());
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
    ChannelTopic topicArticleUpdateReq() {
        return new ChannelTopic(QueueMessageType.UPDATE_ARTICLE_REQ.getChannelName());
    }

    @Bean(name="redisJacksonTemplate")
    public RedisTemplate<String, Object> redisJacksonTemplate(@Qualifier("jedisConnectionFactory") RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(QueueMessage.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setConnectionFactory(factory);
        template.setKeySerializer(redisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }
}
