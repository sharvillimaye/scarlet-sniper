package com.sharvillimaye.backend.dao.impl;

import com.sharvillimaye.backend.dao.RoleDAO;
import com.sharvillimaye.backend.dao.UserDAO;
import com.sharvillimaye.backend.model.Role;
import com.sharvillimaye.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDataAccessService implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private RoleDAO roleDAO;

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
//        var sql = "SELECT * FROM users WHERE username = ?";
//        Optional<User> user = jdbcTemplate.query(sql, userRowMapper, username).stream().findFirst();
//        var sql2 = "SELECT * FROM user_role WHERE user_id = ?";
//        jdbcTemplate.query(sql2, ,user.get().getId());
//
//
//        return jdbcTemplate.query(sql, userRowMapper, username).stream().findFirst();
        String sql = "SELECT * FROM users WHERE username = ?";
        Optional<User> userOpt = jdbcTemplate.query(sql, userRowMapper, username).stream().findFirst();
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String sql2 = """
            SELECT r.authority 
            FROM roles r
            INNER JOIN user_role ur ON ur.role_id = r.role_id
            WHERE ur.user_id = ?
        """;
            List<Role> authorities = jdbcTemplate.query(sql2, (rs, rowNum) ->
                    new Role(rs.getString("authority")), user.getId()
            );
            user.setAuthorities(new HashSet<>(authorities));
            return Optional.of(user);
        }
        return Optional.empty();
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
    public void save(User user) {
        var sql = "INSERT INTO users(username, password, email, phone_number, enabled) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhoneNumber());
            ps.setBoolean(5, user.isEnabled());
            return ps;
        }, keyHolder);
        long userId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        var sql2 = "INSERT INTO user_role(user_id, role_id) VALUES (?, ?)";
        for (var authority : user.getAuthorities()) {
            jdbcTemplate.update(sql2, userId, roleDAO.findByAuthority(authority.getAuthority()).get().getId());
        }
    }

    @Override
    public void update(User user) {
        var sql = "UPDATE users SET username = ?, password = ?, email = ?, phone_number = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNumber(), user.getId());
    }

    @Override
    public Optional<User> delete(long id) {
        Optional<User> user = findById(id);
        jdbcTemplate.update("DELETE FROM users WHERE user_id = ?", id);
        return user;
    }
}
