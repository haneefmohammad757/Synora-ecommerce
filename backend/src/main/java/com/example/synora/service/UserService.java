package com.example.synora.service;

import com.example.synora.model.user;
import com.example.synora.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    // register a new user
    public user registerUser(user user) {

        // username should be unique
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // encrypt password before saving
        user.setPassword(encoder.encode(user.getPassword()));

        // every new registration becomes USER
        user.setRole("USER");

        return repository.save(user);
    }

    // save admin or any user manually
    public user saveUser(user user) {

        user.setPassword(encoder.encode(user.getPassword()));

        return repository.save(user);
    }

    // get user using username
    public user getUserByUsername(String username) {

        return repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}