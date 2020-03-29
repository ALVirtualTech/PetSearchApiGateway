package ru.airlightvt.onlinerecognition.advert.saga.create;

import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;
import ru.airlightvt.onlinerecognition.advert.saga.create.command.CancelCreateAdvertCommand;
import ru.airlightvt.onlinerecognition.advert.saga.create.command.ConfirmCreateAdvertCommand;
import ru.airlightvt.onlinerecognition.advert.saga.create.command.CreateAdvertCommand;
import ru.airlightvt.onlinerecognition.advert.saga.create.reply.CreateAdvertReply;
import ru.airlightvt.onlinerecognition.common.transport.mq.QueueTopic;

public class AdvertServiceProxy {
    public final CommandEndpoint<CreateAdvertCommand> create =
            CommandEndpointBuilder
                .forCommand(CreateAdvertCommand.class)
                .withChannel(QueueTopic.ADVERT_SERVICE_CHANNEL.getChannelName())
                .withReply(CreateAdvertReply.class)
                .build();

    public final CommandEndpoint<CancelCreateAdvertCommand> cancel =
            CommandEndpointBuilder
                    .forCommand(CancelCreateAdvertCommand.class)
                    .withChannel(QueueTopic.ADVERT_SERVICE_CHANNEL.getChannelName())
                    .withReply(Success.class)
                    .build();

    public final CommandEndpoint<ConfirmCreateAdvertCommand> confirmCreate =
            CommandEndpointBuilder
                    .forCommand(ConfirmCreateAdvertCommand.class)
                    .withChannel(QueueTopic.ADVERT_SERVICE_CHANNEL.getChannelName())
                    .withReply(Success.class)
                    .build();
}
