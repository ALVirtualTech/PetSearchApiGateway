package ru.airlightvt.onlinerecognition.advert.saga;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.airlightvt.onlinerecognition.advert.saga.create.command.CancelCreateAdvertCommand;
import ru.airlightvt.onlinerecognition.advert.saga.create.command.ConfirmCreateAdvertCommand;
import ru.airlightvt.onlinerecognition.advert.service.AdvertService;
import ru.airlightvt.onlinerecognition.advert.service.impl.SagaAdvertService;
import ru.airlightvt.onlinerecognition.common.transport.mq.QueueTopic;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

@Component
@AllArgsConstructor
public class AdvertCommandHandlers {
    private final AdvertService advertService;

    public CommandHandlers commandHandlers() {
        return SagaCommandHandlersBuilder
                .fromChannel(QueueTopic.ADVERT_SERVICE_CHANNEL.getChannelName())
                .onMessage(ConfirmCreateAdvertCommand.class, this::approveAdvert)
                .onMessage(CancelCreateAdvertCommand.class, this::cancelCreateAdvert)
                .build();
    }

    public Message approveAdvert(CommandMessage<ConfirmCreateAdvertCommand> commandMessage) {
        long advertId = commandMessage.getCommand().getAdvertId();
        advertService.approveAdvert(advertId);
        return withSuccess();
    }

    public Message cancelCreateAdvert(CommandMessage<CancelCreateAdvertCommand> commandMessage) {
        long advertId = commandMessage.getCommand().getAdvertId();
        advertService.rejectAdvert(advertId);
        return withSuccess();
    }
}
