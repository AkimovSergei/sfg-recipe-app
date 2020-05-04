package com.sa.recipe.converters;

import com.sa.recipe.commands.IngredientCommand;
import com.sa.recipe.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final CommandToUnitOfMeasure commandToUnitOfMeasure;

    public CommandToIngredient(CommandToUnitOfMeasure commandToUnitOfMeasure) {
        this.commandToUnitOfMeasure = commandToUnitOfMeasure;
    }

    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if (null == ingredientCommand) {
            return null;
        }

        return Ingredient.builder()
                .id(ingredientCommand.getId())
                .amount(ingredientCommand.getAmount())
                .description(ingredientCommand.getDescription())
                .unitOfMeasure(commandToUnitOfMeasure.convert(ingredientCommand.getUnitOfMeasureCommand()))
                .build();
    }

}
