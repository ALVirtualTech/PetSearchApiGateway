package ru.airlightvt.onlinerecognition.auth.model;

import org.springframework.security.core.GrantedAuthority;
import ru.airlightvt.onlinerecognition.entity.User;

import java.util.Collection;
import java.util.List;

/**
 * Обертка над пользователем из SpringSecurity, позволяет использовать сущности пользователей из БД при авторизации и аутентификации
 *
 * @author apolyakov
 * @since 06.07.2019
 *
 */
public class AuthorizedUser  extends org.springframework.security.core.userdetails.User {
    public AuthorizedUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    private static final long serialVersionUID = 1L;

    private User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true,
                user.getRoles());
        this.user = user;
    }

    public AuthorizedUser(User user, List<GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true,
                authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return user.getId();
    }

    @Override
    public String toString() {
        return "AuthorizedUser{" +
                "user=" + user +
                '}';
    }
}
