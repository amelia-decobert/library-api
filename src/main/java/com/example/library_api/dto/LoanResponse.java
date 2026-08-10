package com.example.library_api.dto;

import java.time.LocalDate;

public record LoanResponse(
        Long id,
        BookResponse book,
        String username,
        LocalDate loanDate,
        LocalDate dueDate,
        LocalDate returnedDate,
        String status
) {
}
