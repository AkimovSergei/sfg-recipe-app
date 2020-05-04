package com.sa.recipe.converters;

import com.sa.recipe.commands.CategoryCommand;
import com.sa.recipe.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCommand implements Converter<Category, CategoryCommand> {
    @Override
    public CategoryCommand convert(Category category) {
        if (null == category) {
            return null;
        }

        return CategoryCommand.builder()
                .id(category.getId())
                .description(category.getDescription())
                .build();
    }
}
