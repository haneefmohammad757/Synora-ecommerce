package com.example.synora.controller;

import com.example.synora.Security.JwtUtil;
import com.example.synora.dto.LoginResponse;
import com.example.synora.model.user;
import com.example.synora.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public String register(@RequestBody user user) {

        userService.registerUser(user);

        return "Registration Successful";

    }

    // Login user
    @PostMapping("/login")
    public LoginResponse login(

            @RequestParam String username,

            @RequestParam String password

    ) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        username,

                        password

                )

        );

        user currentUser = userService.getUserByUsername(username);

        String token = jwtUtil.generateToken(username);

        return new LoginResponse(

                token,

                currentUser.getId(),

                currentUser.getUsername(),

                currentUser.getRole()

        );

    }

}