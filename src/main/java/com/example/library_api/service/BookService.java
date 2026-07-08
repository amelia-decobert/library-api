package com.example.library_api.service;

import com.example.library_api.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// Define the class as a Bean
@Service
public class BookService {
    // Initialise a list in memory
    private final List<Book> books = List.of(
            new Book(1L, "Clean Code"),
            new Book(2L, "The Rules Of Work"),
            new Book(3L, "Le Parfum")
    );

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(Long id) {
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " not found"));
    }
}
