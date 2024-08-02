package com.sharvillimaye.backend.dto;

import com.sharvillimaye.backend.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

    private User user;
    private String jwt;

    public LoginResponseDTO() {
        super();
    }

    public LoginResponseDTO(User user, String jwt) {
        super();
        this.user = user;
        this.jwt = jwt;
    }
}
