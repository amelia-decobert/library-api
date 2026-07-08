package com.example.library_api.model;

import jakarta.validation.constraints.NotBlank;

public record Book(Long id, @NotBlank(message = "Title can not be empty") String title) {
}
