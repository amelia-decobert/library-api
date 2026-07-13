package com.example.library_api.dto;

import java.util.Set;

public record BookResponse(
        Long id,
        String title,
        AuthorResponse author,
        Set<CategoryResponse> categories,
        Integer publicationYear,
        String isbn) {
}
