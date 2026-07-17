package com.example.library_api.service;

import com.example.library_api.dto.BookRequest;
import com.example.library_api.exception.AuthorNotFoundException;
import com.example.library_api.exception.BookNotFoundException;
import com.example.library_api.mapper.BookMapper;
import com.example.library_api.model.Author;
import com.example.library_api.model.Book;
import com.example.library_api.model.Category;
import com.example.library_api.repository.AuthorRepository;
import com.example.library_api.repository.BookRepository;
import com.example.library_api.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

// Unit tests
// Use Mockito to mock dependencies
// and verify interactions with repositories and mappers
@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BookMapper bookMapper;

// The service under test, with mocked dependencies injected
    @InjectMocks
    private BookService bookService;

    private Book book;
    private Author author;
    private Category category;

//    Set up test fixtures before each test method
//    Initialise a default Author and Book for reuse in tests
    @BeforeEach
    void setUp() {
        author = new Author();
        author.setId(1L);
        author.setName("Robert C. Martin");

        book = new Book();
        book.setId(1L);
        book.setTitle("Clean Code");
        book.setAuthor(author);
        book.setCategories(new HashSet<>());
    }

    @Test
    @DisplayName("getAllBooks should return provided page from the repository")
    void getAllBooks_shouldReturnPageFromRepository() {
        // ARRANGE (set up test data and mock repository behaviour)
        Pageable pageable = PageRequest.of(0, 10);
        Page<Book> page = new PageImpl<>(List.of(book));
        when(bookRepository.findAll(pageable)).thenReturn(page);

        // ACT (call the method under test)
        Page<Book> result = bookService.getAllBooks(pageable);

        // ASSERT (verify the result and repository interaction)
        assertEquals(1, result.getTotalElements());
        assertEquals("Clean Code", result.getContent().get(0).getTitle());
        verify(bookRepository).findAll(pageable);
    }

    @Test
    @DisplayName("getBookById should return the book")
    void getBookById_shouldReturnBook() {
        // ARRANGE
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // ACT
        Book result = bookService.getBookById(1L);

        // ASSERT
        assertEquals("Clean Code", result.getTitle());
        verify(bookRepository).findById(1L);
    }

    @Test
    @DisplayName("getBookById should throw BookNotFoundException when the book does not exist")
    void getBookById_shouldThrowException_whenNotFound() {
        // ARRANGE
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        // ACT & ASSERT (verify exception is thrown)
        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(99L));
        verify(bookRepository).findById(99L);
    }

    @Test
    @DisplayName("createBook should save the book built by the mapper")
    void createBook_shouldSaveAndReturnBook() {
        // ARRANGE
        BookRequest request = new BookRequest("Clean Code", 1L, Set.of(), 2008, "978-1");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
//        Mock the mapper to return the book entity
        when(bookMapper.toEntity(eq("Clean Code"), eq("978-1"), eq(2008), eq(author), anySet())).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);

        // ACT
        Book result = bookService.createBook(request);

        // ASSERT
        assertEquals("Clean Code", result.getTitle());
        verify(authorRepository).findById(1L);
        verify(bookRepository).save(book);
    }

    @Test
    @DisplayName("createBook should throw AuthorNotFoundException and never call save() when the author does not exist")
    void createBook_shouldThrowException_whenAuthorNotFound() {
        // ARRANGE
        BookRequest request = new BookRequest("Clean Code", 99L, Set.of(), 2008, "978-1");
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        // ACT & ASSERT (verify exception is thrown and save() is never called)
        assertThrows(AuthorNotFoundException.class, () -> bookService.createBook(request));
        verify(bookRepository, never()).save(any());
    }

    @Test
    @DisplayName("deleteBook should delete the book")
    void deleteBook_shouldDeleteBook_whenItExists() {
        // ARRANGE
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // ACT
        bookService.deleteBook(1L);

        // ASSERT (verify delete() was called with the book)
        verify(bookRepository).delete(book);
    }

    @Test
    @DisplayName("deleteBook should throw BookNotFoundException and never call delete() when the book does not exist")
    void deleteBook_shouldThrowException_whenNotFound() {
        // ARRANGE
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        // ACT & ASSERT (verify exception is thrown and delete() is never called)
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(99L));
        verify(bookRepository, never()).delete(any());
    }
}
