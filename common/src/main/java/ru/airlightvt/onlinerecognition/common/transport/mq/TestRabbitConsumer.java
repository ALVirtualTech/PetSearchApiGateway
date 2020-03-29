package ru.airlightvt.onlinerecognition.common.transport.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.airlightvt.onlinerecognition.common.transport.TransportConfiguration;

@Component
@ConditionalOnProperty(value="transport.type", havingValue = "rabbitmq")
@Slf4j
@RequiredArgsConstructor
public class TestRabbitConsumer extends TestMessageSubscriber implements MessageListener {
    private final QueueTopic topic = QueueTopic.ADD_ADVERT_REQ;
    private final RabbitTemplate template;

    @Override
    public void onMessage(Message message) {
        log.info("Rabbit Consumer. Received from queue {}: {}", topic.getChannelName(), message);
        MessageConverter messageConverter = template.getMessageConverter();
        ru.airlightvt.onlinerecognition.common.transport.Message o =
                (ru.airlightvt.onlinerecognition.common.transport.Message) messageConverter.fromMessage(message);
        onMessage(o, topic);
    }
}
