package com.sharvillimaye.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationResponseDTO {
    private String username;
    private String email;
    private String phoneNumber;
}
