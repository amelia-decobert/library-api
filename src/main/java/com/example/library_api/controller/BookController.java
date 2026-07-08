package com.example.library_api.controller;

import com.example.library_api.model.Book;
import com.example.library_api.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) { // Get the id in the URL and convert into Long
        return bookService.getBookById(id);
    }
}
