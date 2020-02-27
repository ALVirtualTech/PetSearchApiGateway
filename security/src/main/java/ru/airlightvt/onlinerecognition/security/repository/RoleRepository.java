package ru.airlightvt.onlinerecognition.security.repository;

import org.springframework.data.repository.CrudRepository;
import ru.airlightvt.onlinerecognition.security.entity.Role;

/**
 * CRUD репозиторий для работы с сущностью "Роль"
 * @author apolyakov
 * @since 06.01.2019
 */
public interface RoleRepository  extends CrudRepository<Role, Long> {
    Role getByRoleName(String roleName);
}
