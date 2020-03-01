package ru.airlightvt.onlinerecognition.common.transport.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestRabbitConsumer extends TestMessageSubscriber implements MessageListener {
    private QueueTopic topic = QueueTopic.ADD_ADVERT_REQ;

    RabbitTemplate template;

    @Override
    public void onMessage(Message message) {
        log.info("Rabbit Consumer. Received from queue {}: {}", topic.getChannelName(), message);
        MessageConverter messageConverter = template.getMessageConverter();
        ru.airlightvt.onlinerecognition.common.transport.Message o =
                (ru.airlightvt.onlinerecognition.common.transport.Message) messageConverter.fromMessage(message);
        onMessage(o, topic);
    }
}
