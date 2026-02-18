package com.example.auth.controller;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/request-otp")
    public ResponseEntity<?> requestOtp(@RequestBody Map<String, String> body) {
        authService.requestOtp(body.get("identifier"));
        return ResponseEntity.ok(Map.of("message", "OTP sent"));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> body) {
        try {
            String token = authService.verifyOtp(
                    body.get("identifier"),
                    body.get("otp")
            );
            return ResponseEntity.ok(Map.of("token", token));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", ex.getMessage())
            );
        }
    }
    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestHeader("Authorization") String auth) {

        String token = auth.replace("Bearer ", "");
        String user = authService.getUserByToken(token);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(Map.of("identifier", user));
    }
}

