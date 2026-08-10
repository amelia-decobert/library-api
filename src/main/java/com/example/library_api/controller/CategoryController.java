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
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/categories/{id}")
    public CategoryResponse updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        Category updated = categoryService.updateCategory(id, request.name());

        return categoryMapper.toResponse(updated);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }
}
