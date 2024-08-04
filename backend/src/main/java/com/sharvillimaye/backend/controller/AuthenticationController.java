package com.sharvillimaye.backend.controller;

import com.sharvillimaye.backend.dto.request.LoginRequestDTO;
import com.sharvillimaye.backend.dto.request.RegistrationRequestDTO;
import com.sharvillimaye.backend.dto.request.VerificationRequestDTO;
import com.sharvillimaye.backend.dto.response.LoginResponseDTO;
import com.sharvillimaye.backend.dto.response.RegistrationResponseDTO;
import com.sharvillimaye.backend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequestDTO body) {
        try {
            RegistrationResponseDTO responseDTO = authenticationService.registerUser(body);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO body) {
        try {
            LoginResponseDTO responseDTO = authenticationService.loginUser(body);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
