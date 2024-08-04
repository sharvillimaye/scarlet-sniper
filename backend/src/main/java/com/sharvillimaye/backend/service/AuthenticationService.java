package com.sharvillimaye.backend.service;

import com.sharvillimaye.backend.dto.request.LoginRequestDTO;
import com.sharvillimaye.backend.dto.request.RegistrationRequestDTO;
import com.sharvillimaye.backend.dto.response.LoginResponseDTO;
import com.sharvillimaye.backend.dto.response.RegistrationResponseDTO;
import com.sharvillimaye.backend.exception.InternalServiceError;
import com.sharvillimaye.backend.model.Role;
import com.sharvillimaye.backend.model.User;
import com.sharvillimaye.backend.dao.RoleDAO;
import com.sharvillimaye.backend.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
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

    public RegistrationResponseDTO registerUser(RegistrationRequestDTO body) {
        String username = body.getUsername();
        String password = body.getPassword();
        String email = body.getEmail();
        String phoneNumber = body.getPhoneNumber();

        if (username == null || password == null || username.isBlank() || password.isBlank()) {
            throw new BadCredentialsException("Invalid username or password");
        } else if (email == null && phoneNumber == null) {
            throw new BadCredentialsException("At least one verification method must be provided");
        }

        String encodedPassword = passwordEncoder.encode(body.getPassword());
        Role userRole = roleDAO.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        User user = new User(username, encodedPassword, authorities);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        userDAO.save(user);
        return new RegistrationResponseDTO(username, email, phoneNumber);
    }

    public LoginResponseDTO loginUser(LoginRequestDTO body) {
        String username = body.getUsername();
        String password = body.getPassword();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = jwtService.generateJwt(authentication);
            return new LoginResponseDTO(userDAO.findByUsername(username).get(), token);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password", e);
        } catch (DisabledException e) {
            throw new DisabledException("User is disabled", e);
        } catch (AuthenticationException e) {
            throw new InternalServiceError("Something went wrong");
        }
    }
}
