package ru.airlightvt.onlinerecognition.advert.config;

import io.eventuate.tram.sagas.orchestration.SagaManagerImpl;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import ru.airlightvt.onlinerecognition.advert.saga.AdvertCommandHandlers;
import ru.airlightvt.onlinerecognition.advert.saga.create.AdvertServiceProxy;
import ru.airlightvt.onlinerecognition.advert.saga.create.CreateAdvertSaga;
import ru.airlightvt.onlinerecognition.advert.saga.create.CreateAdvertSagaState;

@Configuration
public class SagaConfiguration {
    @Bean
    @DependsOn("advertServiceProxy")
    public SagaManagerImpl<CreateAdvertSagaState> createAdvertSagaSagaManager(CreateAdvertSaga createAdvertSaga) {
        return new SagaManagerImpl<>(createAdvertSaga);
    }

    @Bean
    public SagaCommandDispatcher advertCommandHandlersDispatcher(AdvertCommandHandlers advertCommandHandlers) {
        return new SagaCommandDispatcher("advertService", advertCommandHandlers.commandHandlers());
    }

    @Bean
    public AdvertServiceProxy advertServiceProxy() {
        return new AdvertServiceProxy();
    }
}
