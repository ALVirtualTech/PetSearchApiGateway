package ru.airlightvt.onlinerecognition.advert.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.eventuate.tram.events.ResultWithEvents;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.airlightvt.onlinerecognition.advert.entity.Advert;
import ru.airlightvt.onlinerecognition.advert.repository.AdvertRepository;
import ru.airlightvt.onlinerecognition.advert.saga.create.CreateAdvertSagaState;
import ru.airlightvt.onlinerecognition.advert.service.AdvertService;
import ru.airlightvt.onlinerecognition.common.data.dto.AdvertDto;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
@AllArgsConstructor
@Slf4j
public class SagaAdvertService  implements AdvertService {
    private final SagaManager<CreateAdvertSagaState> createAdvertSagaStateSagaManager;
    private final DomainEventPublisher eventPublisher;
    private final AdvertRepository advertRepository;

    public Advert createAdvert(AdvertDto advertDto) {
        ResultWithEvents<Advert> advertResultWithEvents = Advert.createAdvert(advertDto);
        Advert advert = Objects.requireNonNull(advertResultWithEvents).result;
        advertRepository.save(advert);
        eventPublisher.publish(Advert.class, Long.toString(advert.getId()), advertResultWithEvents.events);
        CreateAdvertSagaState data = new CreateAdvertSagaState(advert.getId());
        createAdvertSagaStateSagaManager.create(data, Advert.class, advert.getId());
        return advert;
    }

    public Advert approveAdvert(long advertId) {
        return null;
    }

    public void rejectAdvert(long advertId) {
        advertRepository.deleteById(advertId);
    }

    public Set<Advert> getAdverts(long startPosition, int portion)
    {
        int page = (int) startPosition / portion;
        Set<Advert> result = Sets.newHashSet();
        result = Sets.newHashSet(advertRepository.findAll(PageRequest.of(page, portion)).getContent());
        return result;
    }

    @Override
    public List<Advert> findAllUserAdverts(Long userId) {
        return userId == null ? Lists.newArrayList() : advertRepository.getTopByAuthor(userId);
    }

    @Override
    public List<Advert> findAllUserAdverts(Long userId, long startPosition, int portion) {
        int page = (int) startPosition / portion;
        return userId == null ? Lists.newArrayList() : advertRepository.getAdvertByAuthor(userId, PageRequest.of(page, portion)).getContent();
    }

    public Advert getAdvertById(long id) {
        return advertRepository.getAdvertById(id);
    }

    @Override
    public List<Advert> findAll() {
        return Lists.newArrayList(advertRepository.findAll());
    }

    @Override
    public Iterable<Advert> getAdvertsByIds(List<Long> ids) {
        return advertRepository.findAllById(ids);
    }

    public void deleteAdvert(long id) {
        advertRepository.deleteById(id);
    }

    public Advert saveOrUpdateAdvert(Advert advert) {
        return advertRepository.save(advert);
    }

    public List<Advert> saveAdverts(List<Advert> adverts) {
        return Lists.newArrayList(advertRepository.saveAll(adverts));
    }
}
