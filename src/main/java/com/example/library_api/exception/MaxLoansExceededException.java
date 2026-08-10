package com.example.library_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MaxLoansExceededException extends RuntimeException {
    public MaxLoansExceededException(String username) {
        super("User " + username + " has reached the maximum number of active loans (5)");
    }
}
