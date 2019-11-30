package ru.airlightvt.onlinerecognition.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

public enum  Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN,
    NOBODY;

    @Override
    public String getAuthority() {
        return name();
    }
}
