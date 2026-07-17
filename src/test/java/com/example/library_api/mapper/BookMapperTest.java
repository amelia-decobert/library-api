package com.example.library_api.mapper;

import com.example.library_api.dto.BookResponse;
import com.example.library_api.model.Author;
import com.example.library_api.model.Book;
import com.example.library_api.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// Test the conversion between Book entities and BookResponse DTOs
class BookMapperTest {
    private BookMapper bookMapper;
    private Author author;
    private Category category;

//    Initialise test data before each test method
//    Create a BookMapper instance and set up default Author and Cetegory objects
    @BeforeEach
    void setUp() {
        bookMapper = new BookMapper(new AuthorMapper(), new CategoryMapper());
        author = new Author();
        author.setId(1L);
        author.setName("Robert C. Martin");

        category = new Category();
        category.setId(1L);
        category.setName("Informatique");
    }

//    Test the conversion from DTO fields to a Book entity
//    Verify that all fields are correctly mapped
    @Test
    @DisplayName("toEntity should build a Book with all fields given")
    void toEntity_shouldMapAllFields() {
        // ACT
        Book book = bookMapper.toEntity("Clean Code", "978-0132350884", 2008, author, Set.of(category));

        // ASSERT
        assertEquals("Clean Code", book.getTitle());
        assertEquals("978-0132350884", book.getIsbn());
        assertEquals(2008, book.getPublicationYear());
        assertEquals(author, book.getAuthor());
        assertTrue(book.getCategories().contains(category));
    }

//    Test the conversion from a Book entity to a BookResponse DTO
//    Verify that all fields, including nested objects are correctly mapped
    @Test
    @DisplayName("toResponse should convert a Book into BookResponse, including author and categories")
    void toResponse_shouldMapAllFieldsIncludingRelations() {
        // ARRANGE
        Book book = bookMapper.toEntity("Clean Code", "978-0132350884", 2008, author, Set.of(category));
        book.setId(42L);

        // ACT
        BookResponse response = bookMapper.toResponse(book);

        // ASSERT
        assertEquals(42L, response.id());
        assertEquals("Clean Code", response.title());
        assertEquals("Robert C. Martin", response.author().name());
        assertEquals(1, response.categories().size());
    }

//    Test that passing a null Book to the toResponse method throws a NullPointerException
//    Ensure the mapper handles null inputs as expected
    @Test
    @DisplayName("Convert a null book should throw a NullPointerException")
    void toResponse_withNullBook_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> bookMapper.toResponse(null));
    }
}
