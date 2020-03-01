package ru.airlightvt.onlinerecognition.common.transport;

import java.net.URI;

public interface Transport {
    void send(Message message, URI uri);
}
