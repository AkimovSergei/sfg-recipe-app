package com.sa.recipe.services;

import com.sa.recipe.commands.RecipeCommand;
import com.sa.recipe.converters.CommandToRecipe;
import com.sa.recipe.domain.Recipe;
import com.sa.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipesService {
    private final RecipeRepository recipeRepository;
    private final CommandToRecipe commandToRecipe;

    public RecipesService(RecipeRepository recipeRepository, CommandToRecipe commandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.commandToRecipe = commandToRecipe;
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

    public Recipe saveFromCommand(RecipeCommand command) {
        Recipe recipe = commandToRecipe.convert(command);

        if (null == recipe) {
            throw new RuntimeException();
        }

        recipeRepository.save(recipe);

        return recipe;
    }
}
