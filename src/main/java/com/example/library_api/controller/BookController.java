package com.example.library_api.controller;

import com.example.library_api.dto.BookRequest;
import com.example.library_api.dto.BookResponse;
import com.example.library_api.dto.PageResponse;
import com.example.library_api.mapper.BookMapper;
import com.example.library_api.model.Book;
import com.example.library_api.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "Books", description = "APIs for managing books in the library")
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
    private final BookMapper bookMapper;

//    -- GET /books --
    @Operation(
            summary = "Read the list of all books",
            description = "Returns a paginated list of books sorted by title (10 items per page, by default)"
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Books successfully retrieved")
    })

    @GetMapping("/books")
   public PageResponse<BookResponse> getAllBooks(
           @ParameterObject
           @PageableDefault(size = 10, sort = "title") Pageable pageable) {
        Page<Book> page = bookService.getAllBooks(pageable);
        List<BookResponse> content = bookMapper.toResponseList(page.getContent());
        return new PageResponse<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }

//    -- GET /books/{id} --
@Operation(
        summary = "Read the details of a book",
        description = "Gets a book by his id and returns the details of that book"
)

@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Book successfully found"),
        @ApiResponse(responseCode = "404", description = "Book with this id not found")
})

    @GetMapping("/books/{id}")
    public BookResponse getBookById(@PathVariable Long id) { // Get the id in the URL and convert into Long
        return bookMapper.toResponse(bookService.getBookById(id));
    }

//    -- GET /books/author/{author} --
@Operation(
        summary = "Read an author's book list",
        description = "Gets an author by his name and returns a list of books by that author"
)

@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Books by the specified author successfully retrieved")
})

    @GetMapping("/books/author/{author}")
    public List<BookResponse> getBooksByAuthor(@PathVariable String author) {
        return bookMapper.toResponseList(bookService.getBooksByAuthor(author));
    }

//    -- GET /books/recent --
@Operation(
        summary = "Read the list of recent books",
        description = "Returns a list of books published after 2015 (by default)"
)

@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Recent books successfully retrieved"),
})

    @GetMapping("/books/recent")
    public List<BookResponse> getRecentBooks(@RequestParam(defaultValue = "2015") Integer year) {
        return bookMapper.toResponseList(bookService.getRecentBooks(year));
    }

//    -- GET /books/search --
@Operation(
        summary = "Read the results of a specific search",
        description = "Returns search results based on the information provided"
)

@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Search completed successfully")
})

    @GetMapping("/books/search")
    public List<BookResponse> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer year) {
        return bookMapper.toResponseList(bookService.searchBooks(title, author, year));
    }

//    -- POST /books --
@Operation(
        summary = "Create a book",
        description = "Adds a book in database ; Admin rights required"
)

@ApiResponses({
        @ApiResponse(responseCode = "201", description = "Book successfully added"),
        @ApiResponse(responseCode = "400", description = "Invalid book data provided"),
        @ApiResponse(responseCode = "403", description = "User does not have the right to create a book")
})

    @PostMapping("/books")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request) {
        Book created = bookService.createBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.toResponse(created));
    }

//    -- PUT /books/{id} --
@Operation(
        summary = "Update a book",
        description = "Gets a book by his id and modify its data ; Admin rights required"
)

@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Books successfully updated"),
        @ApiResponse(responseCode = "403", description = "User does not have the right to update a book")
})

    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        Book updated = bookService.updateBook(id, request);
        return ResponseEntity.ok(bookMapper.toResponse(updated));
    }

//    -- DELETE /books/{id} --
@Operation(
        summary = "Delete a book",
        description = "Gets a book by his id and removes it from the database ; Admin rights required"
)

@ApiResponses({
        @ApiResponse(responseCode = "204", description = "Books successfully deleted"),
        @ApiResponse(responseCode = "403", description = "User does not have the right to delete a book")
})

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
