package com.example.library_api.dto;

public record AuthResponse(
        String token,
        String email,
        String role) {
}
