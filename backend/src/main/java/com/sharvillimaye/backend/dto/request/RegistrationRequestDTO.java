package com.sharvillimaye.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationRequestDTO {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;

    public RegistrationRequestDTO() {
        super();
    }

    public RegistrationRequestDTO(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
}
