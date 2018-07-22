package ru.airlightvt.onlinerecognition.auth.repository;

import org.springframework.data.repository.CrudRepository;
import ru.airlightvt.onlinerecognition.auth.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
