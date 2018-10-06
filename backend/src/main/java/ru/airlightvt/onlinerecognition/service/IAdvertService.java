package ru.airlightvt.onlinerecognition.service;

import ru.airlightvt.onlinerecognition.entity.Advert;

import java.util.List;

public interface IAdvertService {
    List<Advert> findAllUserAdverts(long userId);

    Iterable<Advert> getAdvertsByIds(List<Long> ids);
}
