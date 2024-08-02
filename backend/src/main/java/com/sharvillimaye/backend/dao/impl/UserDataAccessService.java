package com.sharvillimaye.backend.dao.impl;

import com.sharvillimaye.backend.dao.UserDAO;
import com.sharvillimaye.backend.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDataAccessService implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
//        Integer userId = rs.getInt("user_id");
//        String username = rs.getString("username");
//        String password = rs.getString("password");
//
//    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User deleteById(int id) {
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }
}
