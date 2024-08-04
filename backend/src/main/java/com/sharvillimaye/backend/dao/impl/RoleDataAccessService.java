package com.sharvillimaye.backend.dao.impl;

import com.sharvillimaye.backend.dao.RoleDAO;
import com.sharvillimaye.backend.model.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDataAccessService implements RoleDAO {

    private final JdbcTemplate jdbcTemplate;

    public RoleDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Role> roleRowMapper = (rs, rowNum) -> {
        Role role = new Role();
        role.setId(rs.getLong("role_id"));
        role.setAuthority(rs.getString("authority"));
        return role;
    };

    @Override
    public Optional<Role> findById(long id) {
        var sql = "SELECT * FROM roles WHERE role_id = ?";
        return jdbcTemplate.query(sql, roleRowMapper, id).stream().findFirst();
    }

    @Override
    public Optional<Role> findByAuthority(String authority) {
        var sql = "SELECT * FROM roles WHERE authority = ?";
        return jdbcTemplate.query(sql, roleRowMapper, authority).stream().findFirst();
    }

    @Override
    public List<Role> findAll() {
        var sql = "SELECT * FROM roles LIMIT 100";
        return jdbcTemplate.query(sql, roleRowMapper);
    }

    @Override
    public void save(Role role) {
        var sql = "INSERT INTO roles (role_id, authority) VALUES (?, ?)";
        jdbcTemplate.update(sql, role.getId(), role.getAuthority());
    }

    @Override
    public void update(Role role) {
        var sql = "UPDATE roles SET authority = ? WHERE role_id = ?";
        jdbcTemplate.update(sql, role.getAuthority(), role.getId());
    }

    @Override
    public Optional<Role> delete(long id) {
        Optional<Role> role = findById(id);
        jdbcTemplate.update("DELETE FROM roles WHERE role_id = ?", id);
        return role;
    }
}
