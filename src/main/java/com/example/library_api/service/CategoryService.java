package com.example.library_api.service;

import com.example.library_api.exception.CategoryNotFoundException;
import com.example.library_api.model.Category;
import com.example.library_api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);

        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, String name) {
        Category category = getCategoryById(id);
        category.setName(name);

        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}
