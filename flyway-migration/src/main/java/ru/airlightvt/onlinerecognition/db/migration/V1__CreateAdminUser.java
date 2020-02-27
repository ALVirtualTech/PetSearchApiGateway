package ru.airlightvt.onlinerecognition.db.migration;

import com.google.common.collect.Sets;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.airlightvt.onlinerecognition.db.config.SpringUtils;
import ru.airlightvt.onlinerecognition.security.entity.Role;
import ru.airlightvt.onlinerecognition.security.entity.User;
import ru.airlightvt.onlinerecognition.security.repository.UserRepository;

import java.util.Set;

public class V1__CreateAdminUser extends BaseJavaMigration {
    private final UserRepository userRepository = SpringUtils.getBean(UserRepository.class);
    private final PasswordEncoder encoder = SpringUtils.getBean(PasswordEncoder.class);

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

    @Override
    public void migrate(Context context) throws Exception {
        Role adminRole = Role.ROLE_ADMIN;
        Role userRole = Role.ROLE_USER;
        CheckForAdminUser(Sets.newHashSet(adminRole, userRole));
    }
}
