package ru.airlightvt.onlinerecognition.common.transport.mq;

import lombok.extern.slf4j.Slf4j;
import ru.airlightvt.onlinerecognition.common.transport.Message;

@Slf4j
public class TestMessageSubscriber implements MessageSubscriber {
    @Override
    public void onMessage(Message message, QueueTopic topic) {
        log.info("Received from queue {}: {}", topic.getChannelName(), message);
    }
}
