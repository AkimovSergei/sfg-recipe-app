package com.sa.recipe.bootstrap;

import com.sa.recipe.domain.Difficulty;
import com.sa.recipe.domain.Ingredient;
import com.sa.recipe.domain.Note;
import com.sa.recipe.domain.Recipe;
import com.sa.recipe.repositories.RecipeRepository;
import com.sa.recipe.services.CategoryService;
import com.sa.recipe.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class BootstrapData implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final CategoryService categoryService;
    private final UnitOfMeasureService unitOfMeasureService;

    public BootstrapData(RecipeRepository recipeRepository, CategoryService categoryService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeRepository = recipeRepository;
        this.categoryService = categoryService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("Bootstrap application");

        Recipe recipe = new Recipe();
        recipe.setCookTime(50);
        recipe.setPrepTime(10);
        recipe.setName("Guacamole");
        recipe.setDescription("The best guacamole keeps it simple: just ripe avocados, salt, " +
                "a squeeze of lime, onions, chiles, cilantro, and some chopped tomato. " +
                "Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade.");
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setServings(4);
        recipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipe.setSource("WWW");
        recipe.setDirections("The trick to making perfect guacamole is using ripe avocados that are just the right " +
                "amount of ripeness. Not ripe enough and the avocado will be hard and tasteless. Too ripe and the " +
                "taste will be off.");

        recipe.setNote(
                new Note("The trick to making perfect guacamole is using ripe avocados that are just the right amount " +
                        "of ripeness. Not ripe enough and the avocado will be hard and tasteless. Too ripe and the taste" +
                        " will be off.")
        );

        Ingredient ingredient1 = new Ingredient("avocados", new BigDecimal(2), unitOfMeasureService.findByDescriptionOrCreate("Item"));
        Ingredient ingredient2 = new Ingredient("salt", new BigDecimal(1 / 4), unitOfMeasureService.findByDescriptionOrCreate("Teaspoon"));
        Ingredient ingredient3 = new Ingredient("fresh lime juice", new BigDecimal(1), unitOfMeasureService.findByDescriptionOrCreate("Teaspoon"));

        recipe.addIngredient(ingredient1)
                .addIngredient(ingredient2)
                .addIngredient(ingredient3);

        recipe.addCategory(categoryService.findByDescriptionOrCreate("Mexican"));
        recipe.addCategory(categoryService.findByDescriptionOrCreate("Fast Food"));

        recipeRepository.save(recipe);

        log.debug("Receipe created");
    }
}
