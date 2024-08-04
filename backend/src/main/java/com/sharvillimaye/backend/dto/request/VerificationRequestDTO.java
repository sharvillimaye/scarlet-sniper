package com.sharvillimaye.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationRequestDTO {
    private String email;
    private String phoneNumber;
    private String verificationCode;
}
