package com.sa.recipe.services;

import com.sa.recipe.domain.Category;
import com.sa.recipe.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findByDescriptionOrCreate(String description) {
        Optional<Category> category = categoryRepository.findByDescription("Mexican");

        return category.orElseGet(() -> categoryRepository.save(new Category(description)));
    }
}
