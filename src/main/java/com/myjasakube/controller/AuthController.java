package com.myjasakube.controller;

import com.myjasakube.dto.RegisterRequest;
import com.myjasakube.model.User;
import com.myjasakube.repository.UserRepository;
import com.myjasakube.security.JwtUtil;
import com.myjasakube.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        // Validate if password and confirmPassword match
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Password and Confirm Password do not match.");
        }

        try {
            // Register the user using UserService
            User registeredUser = userService.register(
                    request.getEmail(),
                    request.getPassword(),
                    request.getFullName(),
                    request.getGender(),
                    request.getPhoneNumber(),
                    request.getAddress()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful for user: " + registeredUser.getEmail());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Handle general exceptions
            log.error("Registration error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration.");
        }
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) {
        // Validate input
        if (user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email and password must be provided");
        }

        try {
            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication error");
        }

        // Return hardcoded response instead of generating a JWT token
        return ResponseEntity.ok("SUCCSES"); // Hardcoded JWT token
    }
    // API to get a list of all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
