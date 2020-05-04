package com.sa.recipe.converters;

import com.sa.recipe.commands.UnitOfMeasureCommand;
import com.sa.recipe.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if (null == unitOfMeasureCommand) {
            return null;
        }

        return UnitOfMeasure.builder()
                .id(unitOfMeasureCommand.getId())
                .description(unitOfMeasureCommand.getDescription())
                .build();
    }

}
