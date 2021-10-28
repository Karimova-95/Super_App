package ru.bell.security;

public enum Role {
    USER,
    ADMIN;

    public static Role fromValue(String value) {
        for (Role role : Role.values()) {
            if(role.name().equals(value))
                return role;
        }

        throw new IllegalArgumentException("No such role.");
    }
}
