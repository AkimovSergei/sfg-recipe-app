package com.sa.recipe.services;

import com.sa.recipe.domain.Recipe;
import com.sa.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipesService {
    private final RecipeRepository recipeRepository;

    public RecipesService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Iterable<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }
}
