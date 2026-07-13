package com.example.library_api.mapper;

import com.example.library_api.dto.BookRequest;
import com.example.library_api.dto.BookResponse;
import com.example.library_api.model.Author;
import com.example.library_api.model.Book;
import com.example.library_api.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// Define the class as a Bean Spring
@Component
@RequiredArgsConstructor
public class BookMapper {
    private final AuthorMapper authorMapper;
    private final CategoryMapper categoryMapper;

    public Book toEntity(String title, String isbn, Integer publicationYear, Author author, Set<Category> categories) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPublicationYear(publicationYear);
        book.setAuthor(author);
        book.setCategories(categories);
        return book;
    }

    public BookResponse toResponse(Book book) {
        Set<com.example.library_api.dto.CategoryResponse> categoryResponses = book.getCategories().stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toSet());
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                authorMapper.toResponse(book.getAuthor()),
                categoryResponses,
                book.getPublicationYear(),
                book.getIsbn()
        );
    }

    public List<BookResponse> toResponseList(List<Book> books) {
        return books.stream()
                .map(this::toResponse)
                .toList();
    }
}
