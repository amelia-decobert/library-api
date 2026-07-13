package com.example.library_api.controller;

import com.example.library_api.dto.CategoryRequest;
import com.example.library_api.dto.CategoryResponse;
import com.example.library_api.mapper.CategoryMapper;
import com.example.library_api.model.Category;
import com.example.library_api.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/categories")
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories().stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {
        Category created = categoryService.createCategory(request.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.toResponse(created));
    }
}
