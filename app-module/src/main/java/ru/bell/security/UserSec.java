package ru.bell.security;

import lombok.Data;

@Data
public class UserSec {
    private int id;
    private String groupName;
    private int groupId;
    private String name;
    private String password;

}