package ru.airlightvt.onlinerecognition.config;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.airlightvt.onlinerecognition.entity.Role;
import ru.airlightvt.onlinerecognition.entity.User;
import ru.airlightvt.onlinerecognition.repository.RoleRepository;
import ru.airlightvt.onlinerecognition.repository.UserRepository;

import java.util.Set;

/**
 * Временный инициализатор для создания пользователя admin с заданными правами
 * @since 06.01.2019
 * @author apolyakov
 */
@Component
public class AppInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private boolean alreadySetup = false;
    private PasswordEncoder encoder;

    @Autowired
    public AppInitializer(UserRepository userRepository,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup) {
            return;
        }

        Role adminRole = Role.ROLE_ADMIN;
        Role userRole = Role.ROLE_USER;
        CheckForAdminUser(Sets.newHashSet(adminRole, userRole));

        alreadySetup = true;
    }

    private void CheckForAdminUser(Set<Role> roles) {

        User adminUser = userRepository.getByLogin("admin");

        if(adminUser == null) {
            adminUser = new User();
            adminUser.setLogin("admin");
            adminUser.setEnabled(true);
            adminUser.setPassword(encoder.encode("admin"));
            adminUser.setRoles(roles);
            userRepository.save(adminUser);
        }
    }
}
