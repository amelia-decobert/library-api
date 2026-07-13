package com.example.library_api.dto;

public record StatisticsResponse(
        long books,
        long authors,
        long categories
) {
}
