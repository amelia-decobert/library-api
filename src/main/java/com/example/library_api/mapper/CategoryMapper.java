package com.example.library_api.mapper;

import com.example.library_api.dto.CategoryResponse;
import com.example.library_api.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
