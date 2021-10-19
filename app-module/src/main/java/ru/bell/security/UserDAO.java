//package ru.bell.security;
//
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class UserDAO {
//
//    private final NamedParameterJdbcTemplate namedJdbcTemplate;
//    private final UserMapper mapper;
//
//    public UserDAO(NamedParameterJdbcTemplate namedJdbcTemplate) {
//        this.namedJdbcTemplate = namedJdbcTemplate;
//        this.mapper = new UserMapper();
//    }
//
//    public UserSec findByName(String name) {
//        String sql = "SELECT user.name, user.id, " +
//                "user.password_hash, user.id_group, " +
//                "gr.name AS group_name " +
//                "FROM sec_user AS user " +
//                "LEFT JOIN sec_group AS gr " +
//                "ON user.id_group = gr.id " +
//                "WHERE user.name = :name";
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("name", name);
//        return namedJdbcTemplate.queryForObject(sql, mapSqlParameterSource, mapper);
//    }
//}
