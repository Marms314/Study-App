package com.Learnification.StudyApp.controllers;

import com.Learnification.StudyApp.models.Category;
import com.Learnification.StudyApp.models.data.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value={"", "/index"})
    public String index(Model model) {

        model.addAttribute("title", "Everything, Categorized");

        return "category/index";
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

        model.addAttribute("title", "Manage Categories");

        return "category/manage";
    }

    @PostMapping("manage")
    public String processManageCategoriesForm(Model model) {


        return "redirect:../";
    }

}
