package com.example.auth.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.auth.model.OtpData;

@Service
public class AuthService {

	private Map<String, OtpData> otpStore = new HashMap<>();
	private Map<String, String> tokenStore = new HashMap<>();

	public void requestOtp(String identifier) {

	    OtpData existing = otpStore.get(identifier);

	    if (existing != null &&
	        existing.getBlockedUntil() != null &&
	        existing.getBlockedUntil().isAfter(LocalDateTime.now())) {
	        throw new RuntimeException("User is blocked. Try again after 10 minutes");
	    }

	    String otp = String.valueOf(100000 + new Random().nextInt(900000));
	    LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);

	    otpStore.put(identifier, new OtpData(otp, expiry, 0, null));

	    System.out.println("OTP for " + identifier + " is: " + otp);
	}


	public String verifyOtp(String identifier, String otp) {

		OtpData data = otpStore.get(identifier);

		if (data == null) {
			throw new RuntimeException("OTP not requested");
		}

		if (data.getBlockedUntil() != null && data.getBlockedUntil().isAfter(LocalDateTime.now())) {
			throw new RuntimeException("User is blocked for 10 minutes");
		}

		if (data.getExpiresAt().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("OTP expired");
		}

		if (!data.getOtp().equals(otp)) {
			int attempts = data.getAttempts() + 1;
			data.setAttempts(attempts);

			if (attempts >= 3) {
				data.setBlockedUntil(LocalDateTime.now().plusMinutes(10));
				throw new RuntimeException("Too many wrong attempts. User blocked for 10 minutes");
			}

			throw new RuntimeException("Invalid OTP. Attempts left: " + (3 - attempts));
		}

		String token = UUID.randomUUID().toString();
		tokenStore.put(token, identifier);
		otpStore.remove(identifier);

		return token;
	}

	public String getUserByToken(String token) {
		return tokenStore.get(token);
	}
}
