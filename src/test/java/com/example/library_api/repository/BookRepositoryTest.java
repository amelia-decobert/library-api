package com.example.library_api.repository;

import com.example.library_api.model.Author;
import com.example.library_api.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Testcontainers // Start before running tests & stop after tests
// Ensure that the actual PostgreSQL container is indeed being used
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    @Container // Mark postgre as managed by Testcontainer
    // Configure automatically the datasource of Spring Boot to point to this container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine"); // static which mean shared with all tests in the class

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setName("Robert C. Martin");
        author = authorRepository.save(author);
    }

    @Test
    @DisplayName("save() should persist a book in a real postgresql database")
    void save_shouldPersistBookWithGeneratedId() {
        // ARRANGE
        Book book = new Book();
        book.setTitle("Clean Code");
        book.setAuthor(author);
        book.setCategories(new HashSet<>());

        // ACT
        Book saved = bookRepository.save(book);

        // ASSERT
        assertNotNull(saved.getId());
        assertEquals("Clean Code", saved.getTitle());
    }

    @Test
    @DisplayName("findById() should return a book saved previously")
    void findById_shouldReturnSavedBook() {
        // ARRANGE
        Book book = new Book();
        book.setTitle("1984");
        book.setAuthor(author);
        book.setCategories(new HashSet<>());
        Book saved = bookRepository.save(book);

        // ACT
        Optional<Book> found = bookRepository.findById(saved.getId());

        // ASSERT
        assertTrue(found.isPresent());
        assertEquals("1984", found.get().getTitle());
    }

    @Test
    @DisplayName("delete() should remove an existing book")
    void delete_shouldRemoveBook() {
        // ARRANGE
        Book book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor(author);
        book.setCategories(new HashSet<>());
        Book saved = bookRepository.save(book);

        // ACT
        bookRepository.delete(saved);

        // ASSERT
        assertTrue(bookRepository.findById(saved.getId()).isEmpty());
    }

    @Test
    @DisplayName("Multiple insertions should persist correctly")
    void save_multipleBooks_shouldPersistAllOfThem() {
        // ARRANGE
        Book book1 = new Book();
        book1.setTitle("Clean Code");
        book1.setAuthor(author);
        book1.setCategories(new HashSet<>());

        Book book2 = new Book();
        book2.setTitle("Effective Java");
        book2.setAuthor(author);
        book2.setCategories(new HashSet<>());

        Book book3 = new Book();
        book3.setTitle("Design Patterns");
        book3.setAuthor(author);
        book3.setCategories(new HashSet<>());

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // ACT
        List<Book> allBooks = bookRepository.findAll();

        // ASSERT
        assertEquals(3, allBooks.size());
        assertTrue(allBooks.stream().anyMatch(b -> b.getTitle().equals("Clean Code")));
        assertTrue(allBooks.stream().anyMatch(b -> b.getTitle().equals("Effective Java")));
        assertTrue(allBooks.stream().anyMatch(b -> b.getTitle().equals("Design Patterns")));
    }
}