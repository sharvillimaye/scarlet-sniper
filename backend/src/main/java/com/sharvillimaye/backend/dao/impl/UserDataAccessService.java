package com.sharvillimaye.backend.dao.impl;

import com.sharvillimaye.backend.dao.UserDAO;
import com.sharvillimaye.backend.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDataAccessService implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setEnabled(rs.getBoolean("enabled"));
        return user;
    };

    @Override
    public Optional<User> findById(long id) {
        var sql = "SELECT * FROM users WHERE user_id = ?";
        return jdbcTemplate.query(sql, userRowMapper, id).stream().findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        var sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.query(sql, userRowMapper, username).stream().findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        var sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.query(sql, userRowMapper, email).stream().findFirst();
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        var sql = "SELECT * FROM users WHERE phone_number = ?";
        return jdbcTemplate.query(sql, userRowMapper, phoneNumber).stream().findFirst();
    }

    @Override
    public Optional<User> findByEmailOrPhoneNumber(String email, String phoneNumber) {
        var sql = "SELECT * FROM users WHERE email = ? OR phone_number = ?";
        return jdbcTemplate.query(sql, userRowMapper, email, phoneNumber).stream().findFirst();
    }

    @Override
    public Optional<User> findByVerificationCode(String verificationCode) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        var sql = "SELECT * FROM users LIMIT 100";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public int save(User user) {
        var sql = "INSERT INTO users(username, password, email, phone_number) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNumber());
    }

    @Override
    public int update(User user) {
        var sql = "UPDATE users SET username = ?, password = ?, email = ?, phone_number = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNumber(), user.getId());
    }

    @Override
    public Optional<User> delete(long id) {
        Optional<User> user = findById(id);
        jdbcTemplate.update("DELETE FROM users WHERE user_id = ?", id);
        return user;
    }
}
