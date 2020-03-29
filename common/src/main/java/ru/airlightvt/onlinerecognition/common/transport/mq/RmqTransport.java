package ru.airlightvt.onlinerecognition.common.transport.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.airlightvt.onlinerecognition.common.transport.Message;
import ru.airlightvt.onlinerecognition.common.transport.Transport;

import java.net.URI;

@Component
@ConditionalOnProperty(value="transport.type", havingValue = "rabbitmq")
@Slf4j
public class RmqTransport implements Transport {
    @Autowired
    private AmqpTemplate template;

    @Override
    public void send(Message message, URI uri) {
        log.info("Sending message {} to channel {}", message, uri.getHost());
        template.convertAndSend(uri.getHost(), message);
    }
}
