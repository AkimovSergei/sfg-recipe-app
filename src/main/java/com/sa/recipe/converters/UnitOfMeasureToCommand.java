package com.sa.recipe.converters;

import com.sa.recipe.commands.UnitOfMeasureCommand;
import com.sa.recipe.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if (null == unitOfMeasure) {
            return null;
        }

        return UnitOfMeasureCommand.builder()
                .description(unitOfMeasure.getDescription())
                .id(unitOfMeasure.getId())
                .build();
    }
}

