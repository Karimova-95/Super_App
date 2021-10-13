package ru.bell.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.bell.security.Permissions.*;

public enum Role {
    USER(READ),
    ADMIN(READ, WRITE);

    private final List<Permissions> permissions;

    Role(Permissions... permissions) {
        this.permissions = Arrays.asList(permissions);
    }

    Role(Permissions permission) {
        this.permissions = Collections.singletonList(permission);
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public static Role fromValue(String value) {
        for (Role role : Role.values()) {
            if(role.name().equals(value))
                return role;
        }

        throw new IllegalArgumentException("No such role.");
    }
}
