package com.example.library_api.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Test validation constraints
class BookRequestTest {
    private Validator validator;

//    Initialise a validator before each test
    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

//    Test that a valid BookRequest passes all validation constraints
//    Verify that no violations are returned for a correctly filled request
    @Test
    @DisplayName("A valid request should not throw any violation")
    void validRequest_shouldHaveNoViolations() {
        // ARRANGE
        BookRequest request = new BookRequest("Clean Code", 1L, Set.of(1L), 2008,"978-1");

        // ACT
        Set<ConstraintViolation<BookRequest>> violations = validator.validate(request);

        // ASSERT
        assertTrue(violations.isEmpty());
    }

//    Test that a BookRequest with a blank title fails validation
//    Verify that a violation is returned for an empty title
    @Test
    @DisplayName("An empty title should throw a violation")
    void blankTitle_shouldHaveViolation() {
        // ARRANGE
        BookRequest request = new BookRequest("", 1L, Set.of(1L), 2008, "978-1");

        // ACT
        Set<ConstraintViolation<BookRequest>> violations = validator.validate(request);

        // ASSERT
        assertFalse(violations.isEmpty());
    }

//    Test that a BookRequest with a null authorId fails validation
//    Verify that a violation is returned for a null authorId
    @Test
    @DisplayName("A null authorId should throw a violation")
    void nullAuthorId_shouldHaveViolation() {
        // ARRANGE
        BookRequest request = new BookRequest("1984", null, Set.of(1L), 2008, "978-1");

        // ACT
        Set<ConstraintViolation<BookRequest>> violations = validator.validate(request);

        // ASSERT
        assertFalse(violations.isEmpty());
    }

//    Test that a BookRequest with a negative publication year fails validation
//    Verify that a violation is returned for an invalid year
    @Test
    @DisplayName("A negative publication year should throw a violation")
    void negativePublicationYear_shouldHaveViolation() {
        // ARRANGE
        BookRequest request = new BookRequest("1984", 1L, Set.of(1L), -5, "978-1");

        // ACT
        Set<ConstraintViolation<BookRequest>> violations = validator.validate(request);

        // ASSERT
        assertFalse(violations.isEmpty());
    }
}
