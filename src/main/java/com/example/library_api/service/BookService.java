package com.example.library_api.service;

import com.example.library_api.exception.BookNotFoundException;
import com.example.library_api.model.Book;
import com.example.library_api.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

// Define the class as a Bean
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size)).getContent(); // PostgreSQL returns only necessary via LIMIT/OFFSET (sql)
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getRecentBooks(Integer year) {
        return bookRepository.findByPublicationYearGreaterThan(year);
    }

    public List<Book> searchBooks(String title, String author, Integer year) {
        return bookRepository.search(title, author, year);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existing = getBookById(id);
        existing.setTitle(updatedBook.getTitle());
        existing.setAuthor(updatedBook.getAuthor());
        existing.setPublicationYear(updatedBook.getPublicationYear());
        return bookRepository.save(existing); // Launch an UPDATE sql rather than an INSERT
    }

    public void deleteBook(Long id) {
        Book existing = getBookById(id);
        bookRepository.delete(existing);
    }
}
