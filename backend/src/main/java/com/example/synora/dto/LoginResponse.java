package com.example.synora.dto;

public class LoginResponse {

    private String token;

    private Integer id;

    private String username;

    private String role;

    public LoginResponse(String token, Integer id, String username, String role) {

        this.token = token;
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}