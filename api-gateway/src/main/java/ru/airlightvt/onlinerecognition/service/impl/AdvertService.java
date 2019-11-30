package ru.airlightvt.onlinerecognition.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import ru.airlightvt.onlinerecognition.repository.AdvertRepository;
import ru.airlightvt.onlinerecognition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.airlightvt.onlinerecognition.entity.Advert;
import ru.airlightvt.onlinerecognition.entity.User;
import ru.airlightvt.onlinerecognition.service.IAdvertService;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AdvertService implements IAdvertService {
    private AdvertRepository advertRepository;
    private UserRepository userRepository;

    @Autowired
    public AdvertService(AdvertRepository advertRepository, UserRepository userRepository) {
        this.advertRepository = advertRepository;
        this.userRepository = userRepository;
    }

    public Set<Advert> getAdverts(long startPosition, int portion)
    {
        int page = (int) startPosition / portion;
        Set<Advert> result = Sets.newHashSet();
        result = Sets.newHashSet(advertRepository.findAll(PageRequest.of(page, portion)).getContent());
        return result;
    }

    @Override
    public List<Advert> findAllUserAdverts(long userId) {
        User user = userRepository.findById(userId).orElseGet(null);
        return user == null ? Lists.newArrayList() : advertRepository.getTopByAuthor(user);
    }

    @Override
    public List<Advert> findAllUserAdverts(long userId, long startPosition, int portion) {
        User user = userRepository.findById(userId).orElseGet(null);
        int page = (int) startPosition / portion;
        return user == null ? Lists.newArrayList() : advertRepository.getAdvertByAuthor(user, PageRequest.of(page, portion)).getContent();
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
