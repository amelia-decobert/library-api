package com.example.library_api.dto;

public record BookResponse(
        Long id,
        String title,
        String author,
        String isbn,
        Integer publicationYear) {
}
