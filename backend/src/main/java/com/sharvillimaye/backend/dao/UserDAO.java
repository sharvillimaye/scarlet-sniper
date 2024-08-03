package com.sharvillimaye.backend.dao;

import com.sharvillimaye.backend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> findById(long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByEmailOrPhoneNumber(String email, String phoneNumber);
    Optional<User> findByVerificationCode(String verificationCode);
    List<User> findAll();
    int save(User user);
    int update(User user);
    Optional<User> delete(long id);
}
