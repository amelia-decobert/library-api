package com.example.library_api.service;

import com.example.library_api.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

// Define the class as a Bean
@Service
public class BookService {
    // Initialise a list in memory
    private final List<Book> books = new ArrayList<>(List.of(
            new Book(1L, "Clean Code"),
            new Book(2L, "The Rules Of Work"),
            new Book(3L, "Le Parfum")
    ));

    // Generate unique auto incremented id from the last id
    private final AtomicLong idGenerator = new AtomicLong(3L);

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(Long id) {
        return findBookOrThrow(id);
    }

    public List<Book> searchByTitle(String title) {
        return books.stream()
                .filter(book -> book.title().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

    public Book createBook(Book book) {
        // id generated only by server
        Long newId = idGenerator.incrementAndGet();
        Book created = new Book(newId, book.title());
        books.add(created);
        return created;
    }

    // Book is a record so is impossible to update,
    // Create a new object Book with same id but different title,
    // Then replace previous one int the list
    public Book updateBook(Long id, Book updatedBook) {
        Book existing = findBookOrThrow(id);
        Book updated = new Book(existing.id(), updatedBook.title());
        books.remove(existing);
        books.add(updated);
        return updated;
    }

    public void deleteBook(Long id) {
        Book existing = findBookOrThrow(id);
        books.remove(existing);
    }

    private Book findBookOrThrow(Long id) {
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " not found"));
    }
}
