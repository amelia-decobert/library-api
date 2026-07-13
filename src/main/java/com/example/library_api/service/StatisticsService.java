package com.example.library_api.service;

import com.example.library_api.dto.StatisticsResponse;
import com.example.library_api.repository.AuthorRepository;
import com.example.library_api.repository.BookRepository;
import com.example.library_api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public StatisticsResponse getStatistics() {
        return new StatisticsResponse(
                bookRepository.count(), // Execute SQL request "SELECT COUNT"
                authorRepository.count(),
                categoryRepository.count()
        );
    }
}
