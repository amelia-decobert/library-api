package com.example.library_api.service;

import com.example.library_api.exception.AuthorNotFoundException;
import com.example.library_api.model.Author;
import com.example.library_api.model.Book;
import com.example.library_api.repository.AuthorRepository;
import com.example.library_api.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    public Author createAuthor(String name) {
        Author author = new Author();
        author.setName(name);

        return authorRepository.save(author);
    }

    public List<Book> getBooksByAuthorId(Long id) {
        getAuthorById(id);

        return bookRepository.findByAuthorId(id);
    }

    public Author updateAuthor(Long id, String name) {
        Author author = getAuthorById(id);
        author.setName(name);

        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        Author author = getAuthorById(id);

        authorRepository.delete(author);
    }
}
