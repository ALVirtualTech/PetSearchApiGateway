package ru.airlightvt.onlinerecognition.repository;

import org.springframework.data.repository.CrudRepository;
import ru.airlightvt.onlinerecognition.entity.Advert;
import ru.airlightvt.onlinerecognition.entity.User;

import java.util.List;

public interface AdvertRepository  extends CrudRepository<Advert, Long> {
    List<Advert> getTopByAuthor(User author);
}
