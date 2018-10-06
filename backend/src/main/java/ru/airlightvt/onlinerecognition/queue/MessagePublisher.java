package ru.airlightvt.onlinerecognition.queue;

import ru.airlightvt.onlinerecognition.queue.model.QueueMessage;

/**
 * Отправитель сообщений в очереди сообщений redis
 * @author apolyakov
 * @since 23.06.2018
 */
public interface MessagePublisher {
    void publish(final QueueMessage message);
}
