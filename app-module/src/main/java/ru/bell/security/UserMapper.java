//package ru.bell.security;
//
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UserMapper implements RowMapper<UserSec> {
//
//    @Override
//    public UserSec mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//        UserSec userSec = new UserSec();
//        userSec.setId(resultSet.getInt("id"));
//        userSec.setUsername(resultSet.getString("name"));
//        userSec.setPassword(resultSet.getString("password_hash"));
//        userSec.setGroupId(resultSet.getInt("id_group"));
//        userSec.setGroupName(resultSet.getString("group_name"));
//        return userSec;
//    }
//}
