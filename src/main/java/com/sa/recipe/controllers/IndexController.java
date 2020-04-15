package com.sa.recipe.controllers;

import com.sa.recipe.services.RecipesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private final RecipesService recipesService;

    public IndexController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @RequestMapping({"", "/", "/index"})
    public String index(Model model) {
        model.addAttribute("recipes", recipesService.getRecipes());

        return "index/index";
    }
}
