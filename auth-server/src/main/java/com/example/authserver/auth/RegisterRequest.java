package com.example.authserver.auth;

public record RegisterRequest(String firstname, String lastname, String email, String password) {}
