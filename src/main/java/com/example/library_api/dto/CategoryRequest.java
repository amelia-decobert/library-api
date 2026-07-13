package com.example.library_api.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Name can not be empty")
        String name
) {
}
