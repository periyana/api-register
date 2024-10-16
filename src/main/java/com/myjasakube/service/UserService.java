package com.myjasakube.service;

import com.myjasakube.model.User;
import com.myjasakube.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(String email, String password, String fullName, String gender, String phoneNumber, String address) {
        // Check if email already exists
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email sudah terdaftar.");
        }

        // Buat objek User baru
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // Encode password sebelum disimpan
        user.setFullName(fullName);
        user.setGender(gender);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);

        // Simpan user ke repository
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
