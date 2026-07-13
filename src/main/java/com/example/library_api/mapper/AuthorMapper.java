package com.example.library_api.mapper;

import com.example.library_api.dto.AuthorRequest;
import com.example.library_api.dto.AuthorResponse;
import com.example.library_api.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public Author toEntity(AuthorRequest request) {
        Author author = new Author();
        author.setName(request.name());
        return author;
    }

    public AuthorResponse toResponse(Author author) {
        return new AuthorResponse(
                author.getId(),
                author.getName()
        );
    }
}
