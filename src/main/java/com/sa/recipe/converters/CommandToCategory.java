package com.sa.recipe.converters;

import com.sa.recipe.commands.CategoryCommand;
import com.sa.recipe.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommandToCategory implements Converter<CategoryCommand, Category> {

    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if (null == categoryCommand) {
            return null;
        }

        return Category.builder()
                .id(categoryCommand.getId())
                .description(categoryCommand.getDescription())
                .build();
    }

}
