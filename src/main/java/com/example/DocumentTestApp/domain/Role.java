package com.example.DocumentTestApp.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Данное перечисление было использованно для установления ролей пользователям.
 * Есть обычные USERы, а есть ADMINы - основное отличие в том, что ADMIN может меня права доступа
 * через спесиальную ссылку, которая видна только админу. Логин и пароль админа были установлены через
 * миграцию: admin 1234.
 */
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
