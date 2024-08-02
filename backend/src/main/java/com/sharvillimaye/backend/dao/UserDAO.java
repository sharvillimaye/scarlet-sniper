package com.sharvillimaye.backend.dao;

import com.sharvillimaye.backend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    User findById(Integer id);
    List<User> findAll();
    User save(User user);
    User update(User user);
    User deleteById(int id);
    Optional<User> findByUsername(String username);
}
