package com.example.library_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public record BookRequest(
        @NotBlank(message = "Title can not be empty") String title,
        @NotNull(message = "Author is mandatory") Long authorId,
        Set<Long> categoryIds,
        @Positive(message = "Publication year must be positive") Integer publicationYear,
        String isbn) {
}
