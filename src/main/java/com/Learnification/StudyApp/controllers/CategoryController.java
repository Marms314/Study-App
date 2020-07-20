package com.Learnification.StudyApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("category")
public class CategoryController {

    @RequestMapping(value={"", "/index"})
    public String index(Model model) {

        model.addAttribute("title", "Everything, Categorized");

        return "category/index";
    }

    @GetMapping("create")
    public String renderCreateCategoryForm(Model model) {

        model.addAttribute("title", "New Category");

        return "category/create";
    }

    @PostMapping("create")
    public String processCreateCategoryForm(Model model) {


        return "redirect:../";
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
