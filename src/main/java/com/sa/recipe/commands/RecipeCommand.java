package com.sa.recipe.commands;

import com.sa.recipe.domain.Difficulty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeCommand {
    private Long id;

    private String name;

    private String description;

    private Integer prepTime;

    private Integer cookTime;

    private Integer servings;

    private String source;

    private String url;

    private String directions;

    private Byte[] image;

    private NoteCommand note;

    private Set<IngredientCommand> ingredients = new HashSet<>();

    private Difficulty difficulty;

    private Set<String> categoriesIds = new HashSet<>();
}
