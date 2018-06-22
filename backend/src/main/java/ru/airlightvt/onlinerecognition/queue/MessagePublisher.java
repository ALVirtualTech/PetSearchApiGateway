package ru.airlightvt.onlinerecognition.queue;

/**
 * Отправитель сообщений в очереди сообщений redis
 * @author apolyakov
 * @since 23.06.2018
 */
public interface MessagePublisher {
    void publish(final String message);
}
