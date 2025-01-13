package com.example.JavaProjectPersonalLibrary.services;

import com.example.JavaProjectPersonalLibrary.entities.User;
import com.example.JavaProjectPersonalLibrary.entities.dto.LoginDTO;
import com.example.JavaProjectPersonalLibrary.entities.dto.RegisterDTO;
import com.example.JavaProjectPersonalLibrary.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new EntityExistsException("User with this email already exists!");
        }

        User user = new User(
                registerDTO.getName(),
                registerDTO.getEmail(),
                passwordEncoder.encode(registerDTO.getPassword())
        );

        userRepository.save(user);
    }

    public String login(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return "JWT_TOKEN_PLACEHOLDER";
    }
}
