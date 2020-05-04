package com.sa.recipe.services;

import com.sa.recipe.commands.CategoryCommand;
import com.sa.recipe.converters.CategoryToCommand;
import com.sa.recipe.domain.Category;
import com.sa.recipe.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryToCommand categoryToCommand;

    public CategoryService(CategoryRepository categoryRepository, CategoryToCommand categoryToCommand) {
        this.categoryRepository = categoryRepository;
        this.categoryToCommand = categoryToCommand;
    }

    public Category findByDescriptionOrCreate(String description) {
        Optional<Category> category = categoryRepository.findByDescription("Mexican");

        return category.orElseGet(() -> categoryRepository.save(new Category(description)));
    }

    public HashSet<Category> all () {
        HashSet<Category> list = new HashSet<>();

        categoryRepository.findAll().forEach(list::add);

        return list;
    }

    public Set<CategoryCommand> allAsCommand () {
        HashSet<CategoryCommand> list = new HashSet<>();

        all().forEach(category -> list.add(categoryToCommand.convert(category)));

        return list;
    }

    public Category findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            throw new RuntimeException();
        }

        return category.get();
    }
}
