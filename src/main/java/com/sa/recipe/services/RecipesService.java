package com.sa.recipe.services;

import com.sa.recipe.domain.Recipe;
import com.sa.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipesService {
    private final RecipeRepository recipeRepository;

    public RecipesService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Iterable<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe findById(Long id) {
        Optional<Recipe> optional = recipeRepository.findById(id);

        if (optional.isEmpty()) {
            throw new RuntimeException("Recipe not found");
        }

        return optional.get();
    }
}
