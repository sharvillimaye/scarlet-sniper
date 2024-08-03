package com.sharvillimaye.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class User implements UserDetails {

    private long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private Set<Role> authorities;
    private boolean enabled;
    private VerificationToken verificationToken;

    public User() {
        super();
        this.authorities = new HashSet<>();
        this.enabled = false;
    }

    public User(String username, String password, Set<Role> authorities) {
        super();
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.enabled = false;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
