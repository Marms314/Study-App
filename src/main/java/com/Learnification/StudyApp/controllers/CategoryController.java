package com.Learnification.StudyApp.controllers;

import com.Learnification.StudyApp.models.Category;
import com.Learnification.StudyApp.models.data.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value={"", "/index"})
    public String index(Model model) {

        model.addAttribute("title", "Everything, Categorized");
        model.addAttribute("categories", categoryRepository.findAll());
        return "category/index";
    }

    @RequestMapping("view/{categoryId}")
    public String renderCategory(Model model, @PathVariable int categoryId) {

        Optional optCategory = categoryRepository.findById(categoryId);
        if (optCategory.isPresent()) {
            Category category = (Category) optCategory.get();
            model.addAttribute("title", category.getName());
            model.addAttribute("cardDecks", category.getCardDecks());
            model.addAttribute("quizzes", category.getQuizzes());
            return "category/view";
        }

        return "redirect:/category/index";
    }

    @GetMapping("create")
    public String renderCreateCategoryForm(Model model) {

        model.addAttribute("title", "New Category");
        model.addAttribute(new Category());

        return "category/create";
    }

    @PostMapping("create")
    public String processCreateCategoryForm(@ModelAttribute @Valid Category category, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "New Category");
            return "category/create";
        }

        categoryRepository.save(category);
        return "redirect:index";
    }

    @GetMapping("manage")
    public String renderManageCategoriesForm(Model model) {

        boolean categoriesExist = categoryRepository.count() != 0;
        model.addAttribute("categoriesExist", categoriesExist);
        model.addAttribute("title", "Manage Categories");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("categoryDeleted", true);
        return "category/manage";
    }

    @PostMapping("manage")
    public String processManageCategoriesForm(@RequestParam(required = false) int[] categoryIds, Model model) {

        if (categoryIds != null) {

            for (int id : categoryIds) {
                Optional<Category> currentCategory = categoryRepository.findById(id);
                categoryRepository.delete(currentCategory.get());
            }

            boolean categoriesExist = categoryRepository.count() != 0;
            model.addAttribute("categoriesExist", categoriesExist);
            model.addAttribute("title", "Manage Categories");
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("categoryWasDeleted", true);
            return "category/manage";
        }

        boolean categoriesExist = categoryRepository.count() != 0;
        model.addAttribute("categoriesExist", categoriesExist);
        model.addAttribute("title", "Manage Categories");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("noIdsSelected", true);
        return "category/manage";
    }

}
