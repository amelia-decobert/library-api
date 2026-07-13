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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Define the class as a Bean
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getRecentBooks(Integer year) {
        return bookRepository.findBooksPublishedAfter(year);
    }

    public List<Book> searchBooks(String title, String author, Integer year) {
        return bookRepository.search(title, author, year);
    }

    @Transactional
    public Book createBook(BookRequest request) {
        Author author = authorRepository.findById(request.authorId())
                .orElseThrow(() -> new AuthorNotFoundException(request.authorId()));

        Set<Category> categories = request.categoryIds() == null
                ? new HashSet<>()
                : new HashSet<>(categoryRepository.findAllById(request.categoryIds()));

        Book book = bookMapper.toEntity(request.title(), request.isbn(), request.publicationYear(), author, categories);
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Long id, BookRequest request) {
        Book existing = getBookById(id);

        Author author = authorRepository.findById(request.authorId())
                        .orElseThrow(() -> new AuthorNotFoundException(request.authorId()));

        Set<Category> categories = request.categoryIds() == null
                ? new HashSet<>()
                : new HashSet<>(categoryRepository.findAllById(request.categoryIds()));

        existing.setTitle(request.title());
        existing.setAuthor(author);
        existing.setIsbn(request.isbn());
        existing.setPublicationYear(request.publicationYear());
        existing.setCategories(categories);
        return bookRepository.save(existing); // Launch an UPDATE sql rather than an INSERT
    }

    @Transactional
    public void deleteBook(Long id) {
        Book existing = getBookById(id);
        bookRepository.delete(existing);
    }
}
