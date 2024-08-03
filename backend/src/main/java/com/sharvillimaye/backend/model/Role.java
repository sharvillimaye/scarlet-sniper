package com.sharvillimaye.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
public class Role implements GrantedAuthority {

    private long id;
    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

}