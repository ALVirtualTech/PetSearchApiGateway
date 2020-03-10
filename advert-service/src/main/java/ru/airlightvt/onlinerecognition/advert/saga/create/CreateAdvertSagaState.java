package ru.airlightvt.onlinerecognition.advert.saga.create;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.airlightvt.onlinerecognition.advert.saga.create.command.CancelCreateAdvertCommand;
import ru.airlightvt.onlinerecognition.advert.saga.create.command.ConfirmCreateAdvertCommand;
import ru.airlightvt.onlinerecognition.advert.saga.create.command.CreateAdvertCommand;
import ru.airlightvt.onlinerecognition.advert.saga.create.reply.CreateAdvertReply;

@NoArgsConstructor
@Data
@Builder
@Slf4j
public class CreateAdvertSagaState {
    private long advertId;

    public CreateAdvertSagaState(long advertId) {
        this.advertId = advertId;
    }

    public CreateAdvertCommand makeCreateAdvertCommand() {
        return new CreateAdvertCommand();
    }

    public void handleCreateAdvertReply(CreateAdvertReply reply) {
        log.debug("detAdvertId {}", reply.getAdvertId());
        setAdvertId(reply.getAdvertId());
    }

    public CancelCreateAdvertCommand makeCancelCreateAdvertCommand() {
        return new CancelCreateAdvertCommand(getAdvertId());
    }

    public ConfirmCreateAdvertCommand makeConfirmCreateAdvertCommand() {
        return new ConfirmCreateAdvertCommand(getAdvertId());
    }
}
