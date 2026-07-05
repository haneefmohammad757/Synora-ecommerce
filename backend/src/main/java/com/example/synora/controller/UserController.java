package com.example.synora.controller;

import com.example.synora.model.user;
import com.example.synora.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService service;

    // register a new user
    @PostMapping
    public user createUser(@RequestBody user user) {

        return service.registerUser(user);
    }
}