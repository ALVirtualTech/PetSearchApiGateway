package ru.airlightvt.onlinerecognition.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.airlightvt.onlinerecognition.DAOUser;
import ru.airlightvt.onlinerecognition.entity.Role;
import ru.airlightvt.onlinerecognition.entity.User;

/**
 * Тесты для репозитория для работы с пользователями и ролями
 * @since 1.09.2019
 * @author apolyakov
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveUserWithAdminRIghts()
    {
        //подготовка
        User adminUser = DAOUser.createAdminUser("admin", "admin");
        adminUser.setId(entityManager.persist(adminUser).getId());
        //выполнение действий и проверки
        User userFromDb = userRepository.getByLogin("admin");
        Assert.assertEquals("Пользователь полученный по логину из БД не совпал с ожидаемым!", adminUser, userFromDb);
        userFromDb = userRepository.getById(adminUser.getId());
        Assert.assertEquals("Пользователь полученный по id из БД не совпал с ожидаемым!", adminUser, userFromDb);
    }

    @Test
    public void testGetRoles()
    {
        Role[] roles = Role.values();
        Role adminRole = Role.ROLE_ADMIN;
        Role userRole = Role.ROLE_USER;
        Role noneRole = Role.NOBODY;
        Role[] expectedRoles = new Role[]{userRole, adminRole, noneRole};
        Assert.assertArrayEquals("Набор ролей не совпал с ожидаемым!", expectedRoles, roles);
    }
}
