package com.sharvillimaye.backend.dao;

import com.sharvillimaye.backend.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDAO {
    Optional<Role> findById(long id);
    Optional<Role> findByAuthority(String authority);
    List<Role> findAll();
    int save(Role role);
    int update(Role role);
    Optional<Role> delete(long id);
}
