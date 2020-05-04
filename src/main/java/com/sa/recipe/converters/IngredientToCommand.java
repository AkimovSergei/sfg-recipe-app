package com.sa.recipe.converters;

import com.sa.recipe.commands.IngredientCommand;
import com.sa.recipe.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToCommand unitOfMeasureToCommand;

    public IngredientToCommand(UnitOfMeasureToCommand unitOfMeasureToCommand) {
        this.unitOfMeasureToCommand = unitOfMeasureToCommand;
    }

    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (null == ingredient) {
            return null;
        }

        return IngredientCommand.builder()
                .id(ingredient.getId())
                .amount(ingredient.getAmount())
                .description(ingredient.getDescription())
                .unitOfMeasureCommand(unitOfMeasureToCommand.convert(ingredient.getUnitOfMeasure()))
                .build();
    }
}
