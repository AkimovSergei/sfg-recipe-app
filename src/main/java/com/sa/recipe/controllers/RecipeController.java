package com.sa.recipe.controllers;

import com.sa.recipe.commands.RecipeCommand;
import com.sa.recipe.services.CategoryService;
import com.sa.recipe.services.RecipesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {

    private final RecipesService recipesService;
    private final CategoryService categoryService;

    public RecipeController(RecipesService recipesService, CategoryService categoryService) {
        this.recipesService = recipesService;
        this.categoryService = categoryService;
    }

    @RequestMapping({"/recipe/create"})
    public String create(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        model.addAttribute("categoriesList", categoryService.allAsCommand());

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

    @RequestMapping(value = {"/recipe"}, method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {
        log.debug("Save recipe");

        recipesService.saveFromCommand(command);

        return "redirect:/index";
    }

}
