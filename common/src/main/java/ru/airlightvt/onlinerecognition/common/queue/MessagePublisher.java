package ru.airlightvt.onlinerecognition.common.queue;

import ru.airlightvt.onlinerecognition.common.queue.model.QueueMessage;

/**
 * Отправитель сообщений в очереди сообщений redis
 * @author apolyakov
 * @since 23.06.2018
 */
public interface MessagePublisher {
    void publish(final QueueMessage message);
}
