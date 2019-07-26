package ru.airlightvt.onlinerecognition.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.airlightvt.onlinerecognition.entity.Advert;
import ru.airlightvt.onlinerecognition.entity.User;
import ru.airlightvt.onlinerecognition.repository.AdvertRepository;
import ru.airlightvt.onlinerecognition.repository.UserRepository;
import ru.airlightvt.onlinerecognition.service.IAdvertService;

import java.util.List;

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

    @Override
    public List<Advert> findAllUserAdverts(long userId) {
        User user = userRepository.findById(userId).orElseGet(null);
        return user == null ? Lists.newArrayList() : advertRepository.getTopByAuthor(user);
    }

    @Override
    public List<Advert> findAll() {
        return Lists.newArrayList(advertRepository.findAll());
    }

    @Override
    public Iterable<Advert> getAdvertsByIds(List<Long> ids) {
        return advertRepository.findAllById(ids);
    }
}
