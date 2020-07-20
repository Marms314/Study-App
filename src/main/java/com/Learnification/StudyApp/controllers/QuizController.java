package com.Learnification.StudyApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quiz")
public class QuizController {

    @RequestMapping(value={"", "/index"})
    public String index(Model model) {

        model.addAttribute("title", "Are you ready?");

        return "quiz/index";
    }

    @GetMapping("create")
    public String renderCreateQuizForm(Model model) {

        model.addAttribute("title", "New Quiz");

        return "quiz/create";
    }

    @PostMapping("create")
    public String processCreateQuizForm(Model model) {


        return "redirect:../";
    }

    @GetMapping("manage")
    public String renderManageQuizForm(Model model) {

        model.addAttribute("title", "New Quiz");

        return "quiz/manage";
    }

    @PostMapping("manage")
    public String processManageQuizForm(Model model) {


        return "redirect:../";
    }

}
