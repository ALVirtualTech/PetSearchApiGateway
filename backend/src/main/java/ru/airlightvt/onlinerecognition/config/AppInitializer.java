package ru.airlightvt.onlinerecognition.config;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.airlightvt.onlinerecognition.auth.entity.Role;
import ru.airlightvt.onlinerecognition.auth.entity.User;
import ru.airlightvt.onlinerecognition.auth.repository.RoleRepository;
import ru.airlightvt.onlinerecognition.auth.repository.UserRepository;

import java.util.List;
import java.util.Set;

/**
 * Временный инициализатор для создания пользователя admin с заданными правами
 * @since 06.01.2019
 * @author apolyakov
 */
@Component
public class AppInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AppInitializer(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup) {
            return;
        }

        Role adminRole = CheckForAdminRole();
        Role userRole = CheckForUserRole();
        CheckForAdminUser(Sets.newHashSet(adminRole, userRole));

        alreadySetup = true;
    }

    private Role CheckForUserRole() {
        Role userRole  = roleRepository.getByRoleName("USER");
        if(userRole == null){
            userRole = new Role();
            userRole.setRoleName("USER");
            return roleRepository.save(userRole);
        }
        return  userRole;
    }

    private Role CheckForAdminRole() {
        Role adminRole  = roleRepository.getByRoleName("ADMIN");
        if(adminRole == null){
            adminRole = new Role();
            adminRole.setRoleName("ADMIN");
            return roleRepository.save(adminRole);
        }
        return  adminRole;
    }

    private void CheckForAdminUser(Set<Role> roles) {

        User adminUser = userRepository.getByLogin("admin");

        if(adminUser == null) {
            adminUser = new User();
            adminUser.setLogin("admin");
            adminUser.setEnabled(true);
            adminUser.setPassword(new BCryptPasswordEncoder().encode("admin"));
            adminUser.setRoles(roles);
            userRepository.save(adminUser);
        }
    }
}
