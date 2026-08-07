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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            summary = "Read list books",
            description = "Return a list of books, sorted by title, with pagination"
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Books successfully retrieved")
    })

    @GetMapping("/books")
   public PageResponse<BookResponse> getAllBooks(@PageableDefault(size = 10, sort = "title") Pageable pageable) {
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

//    -- GET /books/{id} -- 401
@Operation(
        summary = "Read book details",
        description = "Return the details of a book by his id"
)

@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Book successfully found"),
        @ApiResponse(responseCode = "404", description = "Book with this id not found")
})

    @GetMapping("/books/{id}")
    public BookResponse getBookById(@PathVariable Long id) { // Get the id in the URL and convert into Long
        return bookMapper.toResponse(bookService.getBookById(id));
    }

//    -- GET /books/author/{author} -- 401
@Operation(
        summary = "",
        description = ""
)

@ApiResponses({
        @ApiResponse(responseCode = "", description = ""),
        @ApiResponse(responseCode = "", description = "")
})

    @GetMapping("/books/author/{author}")
    public List<BookResponse> getBooksByAuthor(@PathVariable String author) {
        return bookMapper.toResponseList(bookService.getBooksByAuthor(author));
    }

//    -- GET /books/recent -- 401
@Operation(
        summary = "",
        description = ""
)

@ApiResponses({
        @ApiResponse(responseCode = "", description = ""),
})

    @GetMapping("/books/recent")
    public List<BookResponse> getRecentBooks(@RequestParam(defaultValue = "2015") Integer year) {
        return bookMapper.toResponseList(bookService.getRecentBooks(year));
    }

//    -- GET /books/search --
@Operation(
        summary = "Read a specific search",
        description = "Return search results based on the information provided"
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
        summary = "Create book",
        description = "Add a book in database"
)

@ApiResponses({
        @ApiResponse(responseCode = "201", description = "Book successfully added"),
        @ApiResponse(responseCode = "403", description = "You do not have the right to create a book")
})

    @PostMapping("/books")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request) {
        Book created = bookService.createBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.toResponse(created));
    }

//    -- PUT /books/{id} --
@Operation(
        summary = "Update book",
        description = "Get a book by his id and modify his data"
)

@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Books successfully updated"),
        @ApiResponse(responseCode = "403", description = "You do not have the right to update a book")
})

    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        Book updated = bookService.updateBook(id, request);
        return ResponseEntity.ok(bookMapper.toResponse(updated));
    }

//    -- DELETE /books/{id} --
@Operation(
        summary = "Delete book",
        description = "Get a book by his id and remove it from the database"
)

@ApiResponses({
        @ApiResponse(responseCode = "204", description = "Books successfully deleted"),
        @ApiResponse(responseCode = "403", description = "You do not have the right to delete a book")
})

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
