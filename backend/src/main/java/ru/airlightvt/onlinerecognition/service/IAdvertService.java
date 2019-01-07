package ru.airlightvt.onlinerecognition.service;

import ru.airlightvt.onlinerecognition.entity.Advert;

import java.util.List;

/**
 * Сервис работы с объявлениями
 */
public interface IAdvertService {
    /**
     * Найти все объявления заданного пользователя
     * @param userId id пользователя
     * @return список объявлений
     */
    List<Advert> findAllUserAdverts(long userId);

    /**
     * Найти все объявления, хранящиеся в системе
     * @return список объявлений
     */
    List<Advert> findAll();

    /**
     * Получить список объявлений по списку id
     * @param ids список идентификаторов объявлений
     * @return список объявлений
     */
    Iterable<Advert> getAdvertsByIds(List<Long> ids);
}
