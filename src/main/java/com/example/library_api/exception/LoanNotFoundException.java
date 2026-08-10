package com.example.library_api.exception;

public class LoanNotFoundException extends NotFoundException {
    public LoanNotFoundException(Long id) {
        super("Loan with id " + id + " not found");
    }
}
