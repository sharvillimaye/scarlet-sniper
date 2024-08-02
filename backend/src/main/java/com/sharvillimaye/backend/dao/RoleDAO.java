package com.sharvillimaye.backend.dao;

import com.sharvillimaye.backend.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDAO {
    Role findById(Integer id);
    List<Role> findAll();
    Role save(Role role);
    Role update(Role role);
    Role deleteById(Integer id);
    Optional<Role> findByAuthority(String authority);
}
