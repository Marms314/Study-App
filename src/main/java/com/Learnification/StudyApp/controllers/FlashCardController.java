package com.Learnification.StudyApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("flashcard")
public class FlashCardController {

    @RequestMapping(value={"", "/index"})
    public String index(Model model) {

        model.addAttribute("title", "Memorization");

        return "flashcard/index";
    }

    @RequestMapping("view")
    public String renderFlashCardDeck(Model model) {

        model.addAttribute("title", "This will be the card deck name");

        return "flashcard/view";
    }

    @GetMapping("create")
    public String renderCreateCardDeckForm(Model model) {

        model.addAttribute("title", "New Card Deck");

        return "flashcard/create";
    }

    @PostMapping("create")
    public String processCreateCardDeckForm(Model model) {


        return "redirect:../";
    }

    @GetMapping("manage")
    public String renderManageCardDeckForm(Model model) {

        model.addAttribute("title", "New Quiz");

        return "flashcard/manage";
    }

    @PostMapping("manage")
    public String processManageCardDeckForm(Model model) {


        return "redirect:../";
    }

}
