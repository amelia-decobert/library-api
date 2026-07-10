package com.example.library_api.controller;

import com.example.library_api.dto.BookRequest;
import com.example.library_api.dto.BookResponse;
import com.example.library_api.mapper.BookMapper;
import com.example.library_api.model.Book;
import com.example.library_api.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Define the class as a REST web controller
@RestController
// Generate automatically a Constructor (Lombok)
@RequiredArgsConstructor
// Equivalent without Lombok
//public BookController(BookService bookService) {
//    this.bookService = bookService;
//}
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/books")
    public List<BookResponse> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return bookMapper.toResponseList(bookService.getAllBooks(page, size));
    }

    @GetMapping("/books/{id}")
    public BookResponse getBookById(@PathVariable Long id) { // Get the id in the URL and convert into Long
        return bookMapper.toResponse(bookService.getBookById(id));
    }

    @GetMapping("/books/author/{author}")
    public List<BookResponse> getBooksByAuthor(@PathVariable String author) {
        return bookMapper.toResponseList(bookService.getBooksByAuthor(author));
    }

    @GetMapping("/books/recent")
    public List<BookResponse> getRecentBooks(@RequestParam Integer year) {
        return bookMapper.toResponseList(bookService.getRecentBooks(year));
    }

    @GetMapping("/books/search")
    public List<BookResponse> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer year) {
        return bookMapper.toResponseList(bookService.searchBooks(title, author, year));
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest request) {
        Book book = bookMapper.toEntity(request);
        Book created = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.toResponse(created));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        Book book = bookMapper.toEntity(request);
        Book updated = bookService.updateBook(id, book);
        return ResponseEntity.ok(bookMapper.toResponse(updated));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
