package ru.airlightvt.onlinerecognition;

import com.google.common.collect.Sets;
import ru.airlightvt.onlinerecognition.entity.Role;
import ru.airlightvt.onlinerecognition.entity.User;

import java.util.Set;

/**
 * Вспомогательный класс, позволяющий создавать модели объектов "Пользователь" предметной области, использующиеся в тестах
 */
public class DAOUser {
    /**
     * Создать модель администратора
     * @param login
     * @param password
     * @return модель пользователя, заполненная необходимыми данными
     */
    public static User createAdminUser(String login, String password)
    {
        Role adminRole = Role.ROLE_ADMIN;
        Role userRole = Role.ROLE_USER;
        Set<Role> roles = Sets.newHashSet(adminRole, userRole);
        User adminUser = new User();
        adminUser.setLogin(login);
        adminUser.setEmail("admin@admin.ru");
        adminUser.setName("admin");
        adminUser.setEnabled(true);
        adminUser.setPassword(password);
        adminUser.setRoles(roles);
        return adminUser;
    }
}
