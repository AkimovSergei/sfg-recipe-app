package com.sa.recipe.controllers;

import com.sa.recipe.services.RecipesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipesService recipesService;

    public RecipeController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @RequestMapping({"/recipe/create"})
    public String create() {
        return "recipe/create";
    }

    @RequestMapping({"/recipe/{id}"})
    public String show(Model model, @PathVariable String id) {

        model.addAttribute("recipe", recipesService.findById(Long.valueOf(id)));

        return "recipe/show";
    }

    @RequestMapping({"/recipe/{id}/edit"})
    public String edit(Model model, @PathVariable String id) {

        model.addAttribute("recipe", recipesService.findById(Long.valueOf(id)));

        return "recipe/edit";
    }


}
