package com.example.JavaProjectPersonalLibrary.controllers;

import com.example.JavaProjectPersonalLibrary.entities.dto.LoginDTO;
import com.example.JavaProjectPersonalLibrary.entities.dto.RegisterDTO;
import com.example.JavaProjectPersonalLibrary.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        authService.register(registerDTO);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String token = authService.login(loginDTO);
        return ResponseEntity.ok("Login successful. Token: " + token);
    }
}