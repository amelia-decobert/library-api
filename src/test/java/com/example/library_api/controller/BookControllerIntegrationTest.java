package com.example.library_api.controller;

import com.example.library_api.model.Author;
import com.example.library_api.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Integration tests
// Use SpringBootTest with a mock web environment and MockMvc to test HTTP endpoints
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Testcontainers

class BookControllerIntegrationTest {
    @Container
    @ServiceConnection

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorRepository authorRepository;

    private Long existingAuthorId;

    @BeforeEach
    void setUp() {
        Author author = new Author();
        author.setName("Robert C. Martin");
        existingAuthorId = authorRepository.save(author).getId();
    }

    @Test
    @WithMockUser(username = "jim", roles = "USER")
    @DisplayName("[GET /books] should return a list of books with status 200")
    void getBooks_shouldReturnPaginatedList() throws Exception {
        // ACT & ASSERT (perform GET request and verify response status and structure)
        mockMvc.perform(get("/books?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalElements").exists());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @DisplayName("[POST /books] should create a book and return a status 201")
    void createBook_shouldReturn201AndCreatedBook() throws Exception {
        // ARRANGE (JSON request body for creating a new book)
        String requestBody = """
            {"title": "Domain-Driven Design",
            "isbn": "9780321125217",
            "publicationYear": 2003,
            "authorId": %d,
            "categoryIds": []
        }
        """.formatted(existingAuthorId);

        // ACT & ASSERT (perform POST request and verify response status and body)
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Domain-Driven Design"))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @WithMockUser(username = "jim", roles = "USER")
    @DisplayName("[GET /books/{id}] should return a status 404 when the id does not exist")
    void getBookById_shouldReturn404_whenNotFound() throws Exception {
        // ACT & ASSERT (perform GET request for a non-existent book and verify error response)
        mockMvc.perform(get("/books/99999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Book with id 99999 not found"));
    }
}
