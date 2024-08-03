package com.sharvillimaye.backend.model;

import com.sharvillimaye.backend.enums.VerificationMethod;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
