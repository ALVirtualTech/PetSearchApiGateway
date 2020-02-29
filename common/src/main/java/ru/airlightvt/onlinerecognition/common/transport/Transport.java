package ru.airlightvt.onlinerecognition.common.transport;

import ru.airlightvt.onlinerecognition.common.transport.mq.Message;

import java.net.URI;

public interface Transport {
    void send(Message message, URI uri);
}
