package com.sharvillimaye.backend.service;

import com.sharvillimaye.backend.dto.LoginResponseDTO;
import com.sharvillimaye.backend.model.Role;
import com.sharvillimaye.backend.model.User;
import com.sharvillimaye.backend.dao.RoleDAO;
import com.sharvillimaye.backend.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public User registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleDAO.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        User user = new User(username, encodedPassword, authorities);
        userDAO.save(user);
        return user;
    }

    public LoginResponseDTO loginUser(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = jwtService.generateJwt(authentication);
            return new LoginResponseDTO(userDAO.findByUsername(username).get(), token);
        } catch (AuthenticationException e) {
            return new LoginResponseDTO(null, "");
        }
    }

}
