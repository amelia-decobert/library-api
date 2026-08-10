package com.example.library_api.controller;

import com.example.library_api.dto.AuthorRequest;
import com.example.library_api.dto.AuthorResponse;
import com.example.library_api.dto.BookResponse;
import com.example.library_api.mapper.AuthorMapper;
import com.example.library_api.mapper.BookMapper;
import com.example.library_api.model.Author;
import com.example.library_api.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    @GetMapping("/authors")
    public List<AuthorResponse> getAllAuthors() {
        return authorService.getAllAuthors().stream()
                .map(authorMapper::toResponse)
                .toList();
    }

    @GetMapping("/authors/{id}")
    public AuthorResponse getAuthorById(@PathVariable Long id) {
        return authorMapper.toResponse(authorService.getAuthorById(id));
    }

    @GetMapping("/authors/{id}/books")
    public List<BookResponse> getBooksByAuthorId(@PathVariable Long id) {
        return bookMapper.toResponseList(authorService.getBooksByAuthorId(id));
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody AuthorRequest request) {
        Author created = authorService.createAuthor(request.name());

        return ResponseEntity.status(HttpStatus.CREATED).body(authorMapper.toResponse(created));
    }

    @PutMapping("/authors/{id}")
    public AuthorResponse updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorRequest request) {
        Author updated = authorService.updateAuthor(id, request.name());

        return authorMapper.toResponse(updated);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);

        return ResponseEntity.noContent().build();
    }
}
