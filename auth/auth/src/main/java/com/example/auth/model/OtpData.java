package com.example.auth.model;

import java.time.LocalDateTime;

public class OtpData {

    private String otp;
    private LocalDateTime expiresAt;
    private int attempts;
    private LocalDateTime blockedUntil;

    public OtpData(String otp, LocalDateTime expiresAt, int attempts, LocalDateTime blockedUntil) {
        this.otp = otp;
        this.expiresAt = expiresAt;
        this.attempts = attempts;
        this.blockedUntil = blockedUntil;
    }

    public String getOtp() {
        return otp;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public LocalDateTime getBlockedUntil() {
        return blockedUntil;
    }

    public void setBlockedUntil(LocalDateTime blockedUntil) {
        this.blockedUntil = blockedUntil;
    }
}
