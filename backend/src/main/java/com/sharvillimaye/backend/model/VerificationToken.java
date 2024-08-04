package com.sharvillimaye.backend.model;

import com.sharvillimaye.backend.enums.VerificationMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class VerificationToken {
    private long id;
    private String token;
    private VerificationMethod verificationMethod;
    private LocalDateTime verificationCodeExpiresAt;
    private boolean used;

    public VerificationToken() {
        this.used = false;
    }

    public VerificationToken(String token, VerificationMethod verificationMethod, LocalDateTime verificationCodeExpiresAt) {
        this.token = token;
        this.verificationMethod = verificationMethod;
        this.verificationCodeExpiresAt = verificationCodeExpiresAt;
        this.used = false;
    }
}
