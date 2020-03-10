package ru.airlightvt.onlinerecognition.advert.saga.create;

import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import io.eventuate.tram.sagas.simpledsl.StepBuilder;
import org.springframework.stereotype.Component;
import ru.airlightvt.onlinerecognition.advert.saga.create.reply.CreateAdvertReply;

@Component
public class CreateAdvertSaga implements SimpleSaga<CreateAdvertSagaState> {
    private SagaDefinition<CreateAdvertSagaState> sagaDefinition;

    public CreateAdvertSaga(AdvertServiceProxy advertServiceProxy) {
        this.sagaDefinition =
                step()
                        .invokeParticipant(advertServiceProxy.create,
                                CreateAdvertSagaState::makeCreateAdvertCommand)
                        .onReply(CreateAdvertReply.class,
                                CreateAdvertSagaState::handleCreateAdvertReply)
                        .withCompensation(advertServiceProxy.cancel,
                                CreateAdvertSagaState::makeCancelCreateAdvertCommand)
                .step()
                        .invokeParticipant(advertServiceProxy.confirmCreate,
                                CreateAdvertSagaState::makeConfirmCreateAdvertCommand)
                .build();
    }

    @Override
    public SagaDefinition<CreateAdvertSagaState> getSagaDefinition() {
        return sagaDefinition;
    }

    @Override
    public StepBuilder<CreateAdvertSagaState> step() {
        return null;
    }
}
