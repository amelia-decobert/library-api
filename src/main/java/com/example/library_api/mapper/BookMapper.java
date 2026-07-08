package com.example.library_api.mapper;

import com.example.library_api.dto.BookRequest;
import com.example.library_api.dto.BookResponse;
import com.example.library_api.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

// Define the class as a Bean Spring
@Component
public class BookMapper {
    public Book toEntity(BookRequest request) {
        return new Book(null, request.title());
    }

    public BookResponse toResponse(Book book) {
        return new BookResponse(book.id(), book.title());
    }

    public List<BookResponse> toResponseList(List<Book> books) {
        return books.stream()
                .map(this::toResponse)
                .toList();
    }
}
