package com.sharvillimaye.backend.dao.impl;

import com.sharvillimaye.backend.dao.RoleDAO;
import com.sharvillimaye.backend.model.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDataAccessService implements RoleDAO {

    private final JdbcTemplate jdbcTemplate;

    public RoleDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Role findById(Integer id) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return List.of();
    }

    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public Role deleteById(Integer id) {
        return null;
    }

    @Override
    public Optional<Role> findByAuthority(String authority) {
        return Optional.empty();
    }
}
