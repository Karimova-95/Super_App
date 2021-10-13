package ru.bell.security;

import org.springframework.stereotype.Service;

@Service
public class UserService{

    private final UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public UserSec getByName(String name) {
        return dao.findByName(name);
    }
}