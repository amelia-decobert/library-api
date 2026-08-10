package com.example.library_api.dto;

import jakarta.validation.constraints.NotNull;

public record LoanRequest(@NotNull(message = "Book id is mandatory") Long bookId) {
}
