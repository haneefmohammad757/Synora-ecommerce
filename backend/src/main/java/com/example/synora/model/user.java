package com.example.synora.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class user {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Username should be unique
    @Column(unique = true)
    private String username;

    // Encrypted password will be stored here
    private String password;

    // ROLE_ADMIN or ROLE_USER
    private String role;

    // Empty constructor (required by JPA)
    public user() {
    }

    // Constructor for creating users
    public user(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}