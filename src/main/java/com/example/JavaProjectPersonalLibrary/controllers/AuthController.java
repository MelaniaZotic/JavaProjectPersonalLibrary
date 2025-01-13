package com.example.JavaProjectPersonalLibrary.controllers;

import com.example.JavaProjectPersonalLibrary.entities.dto.LoginDTO;
import com.example.JavaProjectPersonalLibrary.entities.dto.RegisterDTO;
import com.example.JavaProjectPersonalLibrary.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "APIs for user authentication and registration")

public class AuthController {

    @Autowired
    private  AuthService authService;


    @Operation(
            summary = "Register a new user",
            description = "Create a new user account with name, email, and password"
    )
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        authService.register(registerDTO);
        return ResponseEntity.ok("User registered successfully!");
    }


    @Operation(
            summary = "Login a user",
            description = "Authenticate a user and return a JWT token upon successful login"
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String token = authService.login(loginDTO);
        return ResponseEntity.ok("Login successful. Token: " + token);
    }
}