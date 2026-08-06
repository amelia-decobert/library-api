package com.example.library_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenLoanAccessException extends RuntimeException {
    public ForbiddenLoanAccessException() {
        super("You are not allowed to access this loan");
    }
}
