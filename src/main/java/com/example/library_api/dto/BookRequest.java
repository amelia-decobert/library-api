package com.example.library_api.dto;

import jakarta.validation.constraints.NotBlank;

// Use NotBlank validation
public record BookRequest(@NotBlank(message = "Title can not be empty") String title) {
}
