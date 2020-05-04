package com.sa.recipe.converters;

import com.sa.recipe.commands.RecipeCommand;
import com.sa.recipe.domain.Recipe;
import com.sa.recipe.services.CategoryService;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CommandToNote commandToNote;
    private final CommandToIngredient commandToIngredient;
    private final CommandToCategory commandToCategory;
    private final CategoryService categoryService;

    public CommandToRecipe(CommandToNote commandToNote, CommandToIngredient commandToIngredient, CommandToCategory commandToCategory, CategoryService categoryService) {
        this.commandToNote = commandToNote;
        this.commandToIngredient = commandToIngredient;
        this.commandToCategory = commandToCategory;
        this.categoryService = categoryService;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (null == recipeCommand) {
            return null;
        }

        final Recipe recipe = Recipe.builder()
                .id(recipeCommand.getId())
                .name(recipeCommand.getName())
                .description(recipeCommand.getDescription())
                .cookTime(recipeCommand.getCookTime())
                .difficulty(recipeCommand.getDifficulty())
                .directions(recipeCommand.getDirections())
                .image(recipeCommand.getImage())
                .prepTime(recipeCommand.getPrepTime())
                .servings(recipeCommand.getServings())
                .source(recipeCommand.getSource())
                .url(recipeCommand.getUrl())
                .build();

        recipe.setNote(commandToNote.convert(recipeCommand.getNote()));

        if (null != recipeCommand.getIngredients()) {
            recipeCommand.getIngredients().forEach(
                    ingredientCommand -> recipe.addIngredient(commandToIngredient.convert(ingredientCommand))
            );
        }

//        if (null != recipeCommand.getCategories()) {
//            recipeCommand.getCategories().forEach(
//                    categoryCommand -> recipe.addCategory(commandToCategory.convert(categoryCommand))
//            );
//        }

        if (null != recipeCommand.getCategoriesIds()) {
            recipeCommand.getCategoriesIds().forEach(
                    categoryId -> recipe.addCategory(categoryService.findById(Long.valueOf(categoryId)))
            );
        }

        return recipe;
    }

}
