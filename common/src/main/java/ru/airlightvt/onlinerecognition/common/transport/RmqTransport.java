package ru.airlightvt.onlinerecognition.common.transport;

import org.springframework.stereotype.Component;
import ru.airlightvt.onlinerecognition.common.transport.mq.Message;

import java.net.URI;

// todo: for RabbitMQ transport
@Component
//@ConditionalOnExpression("${transport.type}")
public class RmqTransport implements Transport {
    @Override
    public void send(Message message, URI uri) {

    }
}
