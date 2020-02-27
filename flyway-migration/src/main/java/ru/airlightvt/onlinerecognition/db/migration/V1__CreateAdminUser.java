package ru.airlightvt.onlinerecognition.db.migration;

import com.google.common.collect.Sets;
import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.airlightvt.onlinerecognition.security.entity.Role;
import ru.airlightvt.onlinerecognition.security.entity.User;
import ru.airlightvt.onlinerecognition.security.repository.UserRepository;

import java.util.Set;

@Component
public class V1__CreateAdminUser implements SpringJdbcMigration {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public V1__CreateAdminUser(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void migrate(JdbcTemplate jdbcTemplate) {
        Role adminRole = Role.ROLE_ADMIN;
        Role userRole = Role.ROLE_USER;
        CheckForAdminUser(Sets.newHashSet(adminRole, userRole));
    }

    private void CheckForAdminUser(Set<Role> roles) {
        User adminUser = userRepository.getByLogin("admin");

        if(adminUser == null) {
            adminUser = new User();
            adminUser.setLogin("admin");
            adminUser.setEmail("admin@admin.ru");
            adminUser.setName("admin");
            adminUser.setEnabled(true);
            adminUser.setPassword(encoder.encode("admin"));
            adminUser.setRoles(roles);
            userRepository.save(adminUser);
        }
    }
}
