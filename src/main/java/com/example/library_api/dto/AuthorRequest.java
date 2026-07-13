package com.example.library_api.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthorRequest(
        @NotBlank(message = "Author can not be empty")
        String name
) {
}
