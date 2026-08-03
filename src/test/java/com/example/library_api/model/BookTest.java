package com.example.library_api.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

//Test Constructor, setters and default behaviour
class BookTest {
//    Test the no args Constructor
//    Verify that a Book instance created with the no args Constructor has null fields
    @Test
    @DisplayName("Constructor without argument should create a book with empty fields")
    void noArgsConstructor_shouldCreateEmptyBook() {
        // ACT
        Book book = new Book();

        // ASSERT
        assertNull(book.getId());
        assertNull(book.getTitle());
    }

//    Test the all args Constructor
//    Verify that all fields are correctly initialised
    @Test
    @DisplayName("Entire Constructor should initialise all fields ")
    void allArgsConstructor_shouldSetAllFields() {
        // ARRANGE
        Author author = new Author();
        author.setName("Robert C. Martin");
        Set<Category> categories = new HashSet<>();

        // ACT
        Book book = new Book(1L, "Clean Code", "978-1", 2008, author, categories);

        // ASSERT
        assertEquals(1L, book.getId());
        assertEquals("Clean Code", book.getTitle());
        assertEquals("978-1", book.getIsbn());
        assertEquals(2008, book.getPublicationYear());
        assertEquals(author, book.getAuthor());
        assertEquals(categories, book.getCategories());
    }

//    Test the setters of the Book class
//    Verify that fields can be updated using setters
    @Test
    @DisplayName("Setters should update fields")
    void setters_shouldUpdateFields() {
        // ARRANGE
        Book book = new Book();

        // ACT
        book.setTitle("1984");
        book.setPublicationYear(1949);

        // ASSERT
        assertEquals("1984", book.getTitle());
        assertEquals(1949, book.getPublicationYear());
    }

//    Test the default equals() behaviour
//    Verify that two different Book instances with the same field values
//    are not considered equal by default
    @Test
    @DisplayName("equals() by default compare references, not fields")
    void equals_shouldBeReferenceBased() {
        // ARRANGE
        Book book1 = new Book();
        book1.setTitle("1984");

        Book book2 = new Book();
        book2.setTitle("1984");

        // ASSERT
        assertFalse(book1.equals(book2), "2 different instances should not be equals by default");
        assertEquals(book1, book1, "An instance is always equals to itself");
    }
}
