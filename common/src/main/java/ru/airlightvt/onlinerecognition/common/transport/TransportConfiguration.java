package ru.airlightvt.onlinerecognition.common.transport;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ru.airlightvt.onlinerecognition.common.queue.model.QueueMessage;

import java.util.List;

@Configuration
public class TransportConfiguration {
    @Configuration
    @ConditionalOnProperty(value="transport.type", havingValue = "rabbitmq")
    @EnableRabbit
    @EnableAutoConfiguration(exclude = {RedisAutoConfiguration.class})
    public static class RabbitMqConfiguration {
        //настраиваем соединение с RabbitMQ
        @Bean
        public ConnectionFactory connectionFactory(@Value("${rabbit.host}") String host, @Value("${rabbit.port}") int port) {
            return new CachingConnectionFactory(host, port);
        }

        @Bean
        public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
            return new RabbitAdmin(connectionFactory);
        }

        @Bean
        public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
            return new RabbitTemplate(connectionFactory);
        }


    }

    /**
     * Конфигурация распределенной очереди сообщений Redis
     * @author apolyakov
     * @since 23.06.2018
     */
    @Configuration
    @ConditionalOnProperty(value="transport.type", havingValue = "redis")
    public static class RedisMqConfiguration {
        /**
         * Фабрика для конструирования соединия с redis
         * @return JedisConnectionFactory
         */
//        @Bean
//        public JedisConnectionFactory jedisConnectionFactory() {
//            return new JedisConnectionFactory();
//        }

        /**
         * Шаблон записей для помещения в очередь сообщений redis
         * key: String, value: Serializable to String object
         * @return RedisTemplate<String, Object>
         */
        @Bean(name="redisGenericTemplate")
        public RedisTemplate<String, QueueMessage> redisGenericTemplate(RedisConnectionFactory factory) {
            final RedisTemplate<String, QueueMessage> template = new RedisTemplate<>();
            template.setConnectionFactory(factory);
            template.setValueSerializer(new GenericToStringSerializer<>(QueueMessage.class));
            return template;
        }

        @Bean(name="redisJacksonTemplate")
        public RedisTemplate<String, Message> redisJacksonTemplate(RedisConnectionFactory factory) {
            RedisTemplate<String, Message> template = new RedisTemplate<>();
            RedisSerializer<String> redisSerializer = new StringRedisSerializer();
            Jackson2JsonRedisSerializer<Message> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Message>(Message.class);

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

        /**
         * Контейнер слушателей сообщений из очередей redis
         * TODO поменять канал в очереди redis
         * TODO найти аналог @EnableRabbit для redis
         * TODO создать базовый тип для наших MessageListener, из которого можно получить топик (название очереди)
         */
        @Bean
        RedisMessageListenerContainer redisContainer(List<MessageListener> messageListeners, RedisConnectionFactory factory) {
            final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
            container.setConnectionFactory(factory);
            if (messageListeners != null) {
                for (MessageListener messageListener : messageListeners) {
                    container.addMessageListener(messageListener, () -> "TEST");
                }
            }
            return container;
        }

        /**
         * Получатель сообщений из очереди redis
         * defaultListenerMethod = MessageSubscriber#onMessage
         * TODO стоит ли использовать обертку MessageListenerAdapter?
         * @return MessageListenerAdapter
         */
//        @Bean
//        MessageListenerAdapter messageListener(CountDownLatch latch) {
//            return new MessageListenerAdapter(new MessageSubscriber(latch, redisJacksonTemplate(jedisConnectionFactory())));
//        }
    }
}
