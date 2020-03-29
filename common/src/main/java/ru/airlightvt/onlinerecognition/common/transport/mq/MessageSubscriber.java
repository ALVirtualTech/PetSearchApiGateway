package ru.airlightvt.onlinerecognition.common.transport.mq;

import ru.airlightvt.onlinerecognition.common.transport.Message;

public interface MessageSubscriber {
    void onMessage(Message message, QueueTopic topic);
}
