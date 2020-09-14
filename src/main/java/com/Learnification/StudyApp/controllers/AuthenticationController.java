package com.Learnification.StudyApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    @RequestMapping("/login")
    public String login(@RequestParam(required = false) boolean loggedOut, Model model) {
        model.addAttribute("title", "Welcome to StudyTime!");
        model.addAttribute("loggedOut", loggedOut);
        return "login";
    }

    @RequestMapping("/access-denied")
    public String accessDenied() {
        return "403";
    }
}