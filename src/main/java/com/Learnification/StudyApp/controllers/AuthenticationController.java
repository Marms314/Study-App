package com.Learnification.StudyApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Welcome to StudyTime!");
        return "login";
    }
}