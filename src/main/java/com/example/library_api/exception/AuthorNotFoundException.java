package com.example.library_api.exception;

public class AuthorNotFoundException extends NotFoundException {
    public AuthorNotFoundException(Long id) {
        super("Author with id " + id + " not found");
    }
}
