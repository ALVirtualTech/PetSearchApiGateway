package ru.airlightvt.onlinerecognition.advert.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.airlightvt.onlinerecognition.advert.entity.Advert;
import ru.airlightvt.onlinerecognition.advert.repository.AdvertRepository;
import ru.airlightvt.onlinerecognition.advert.service.IAdvertService;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AdvertService implements IAdvertService {
    private AdvertRepository advertRepository;

    @Autowired
    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
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
