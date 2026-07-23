package com.example.library_api.repository;

import com.example.library_api.model.Author;
import com.example.library_api.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Use an in-memory H2 db to test JPA repository operations
// DataJpaTest configures an embedded db and scans for @Entity classes and Spring Data JPA repositories
@DataJpaTest
class BookRepoTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;

//    Set up test fixtures before each test method
//    Save a default Author to the db for reuse in tests
    @BeforeEach
    void setUp() {
        author = new Author();
        author.setName("Robert C. Martin");
        author = authorRepository.save(author);
    }

    @Test
    @DisplayName("save() should persist a book and generate an id")
    void save_shouldPersistBookWithGeneratedId() {
        // ARRANGE (create a new book with the saved author)
        Book book = new Book();
        book.setTitle("Clean Code");
        book.setAuthor(author);
        book.setCategories(new HashSet<>());

        // ACT (save the book to the repository)
        Book saved = bookRepository.save(book);

        // ASSERT (verify the book was saved with a generated id and correct title)
        assertNotNull(saved.getId());
        assertEquals("Clean Code", saved.getTitle());
    }

    @Test
    @DisplayName("findById() should return a saved book")
    void FindById_shouldReturnSavedBook() {
        // ARRANGE (create and save a book)
        Book book = new Book();
        book.setTitle("1984");
        book.setAuthor(author);
        book.setCategories(new HashSet<>());
        Book saved = bookRepository.save(book);

        // ACT (retrieve the book by its id)
        Optional<Book> found = bookRepository.findById(saved.getId());

        // ASSERT (verify the book was found and has the correct title)
        assertTrue(found.isPresent());
        assertEquals("1984", found.get().getTitle());
    }

    @Test
    @DisplayName("findById() should return an empty Optional when the id does not exist")
    void findById_shouldReturnEmpty_whenNotFound() {
        // ACT (attempt to find a book with a non-existent id)
        Optional<Book> found = bookRepository.findById(999L);

        // ASSERT (verify no book was found)
        assertTrue(found.isEmpty());
    }

    @Test
    @DisplayName("delete() should remove a book")
    void delete_shouldRemoveBook() {
        // ARRANGE (create and save a book)
        Book book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor(author);
        book.setCategories(new HashSet<>());
        Book saved = bookRepository.save(book);

        // ACT (delete the book)
        bookRepository.delete(saved);

        // ASSERT (verify the book no longer exists in the repository)
        assertTrue(bookRepository.findById(saved.getId()).isEmpty());
    }
}
