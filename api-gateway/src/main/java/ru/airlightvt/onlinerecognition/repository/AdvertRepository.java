package ru.airlightvt.onlinerecognition.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.airlightvt.onlinerecognition.entity.Advert;
import ru.airlightvt.onlinerecognition.entity.User;

import java.util.List;

/**
 * CRUD репозиторий для работы с сущностью "Объявление"
 *
 * @author apolyakov
 * @since 06.01.2019
 */
public interface AdvertRepository  extends CrudRepository<Advert, Long> {
    /**
     * Получить заданную часть из всех объявлений
     * @param pageable страница от-до
     * @return набор объявлений
     */
    Page<Advert> findAll(Pageable pageable);

    /**
     * Получить объявление по его идентификатору
     * @param id идентификатор объявления
     * @return объявление с заданным id
     */
    Advert getAdvertById(long id);

    /**
     * Получить все объявления заданного автора
     * @param author автор объявлений
     * @return набор объявлений
     */
    List<Advert> getAdvertByAuthor(User author);

    Page<Advert> getAdvertByAuthor(User author, Pageable pageable);

    List<Advert> getTopByAuthor(User author);
}
