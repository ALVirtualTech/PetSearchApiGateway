package ru.airlightvt.onlinerecognition.security.service;

import ru.airlightvt.onlinerecognition.security.entity.Role;
import ru.airlightvt.onlinerecognition.security.entity.User;
import ru.airlightvt.onlinerecognition.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.airlightvt.onlinerecognition.security.model.AuthorizedUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Кастомный сервис авторизации, используя Spring Data JPA
 * @author apolyakov
 * @since 06.01.2019
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Получить данные авторизации о пользователе по его логину.
     * Многие параметры захардкожены: enabled, accountNonExpired, credentialsNonExpired, accountNonLocked
     *
     * @param username логин пользователя
     * @return данные авторизации пользователя
     * @throws UsernameNotFoundException пользователь не найден
     */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new AuthorizedUser(user, getGrantedAuthorities(user));
    }

    /**
     * Доступные для пользователя права доступа на основе ролей
     *
     * @param user пользователь, проходящий проверку прав
     * @return список прав доступа
     */
    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        // ! ADMIN has USER authority
        return authorities;
    }
}
