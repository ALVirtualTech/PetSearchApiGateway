package ru.airlightvt.onlinerecognition.security;

import ru.airlightvt.onlinerecognition.security.model.AuthorizedUser;
import ru.airlightvt.onlinerecognition.security.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.requireNonNull;

/**
 * Вспомогательные методы для работы с контекстом авторизации
 * 
 * @author apolyakov
 * @since 06.11.2019
 */
public class SecurityUtil {
    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static long authorizedUserId() {
        return get().getId();
    }

    public static User getUser() {
        return get().getUser();
    }
}
