package com.Learnification.StudyApp.controllers;

import com.Learnification.StudyApp.models.CardDeck;
import com.Learnification.StudyApp.models.FlashCard;
import com.Learnification.StudyApp.models.data.CardDeckRepository;
import com.Learnification.StudyApp.models.data.CategoryRepository;
import com.Learnification.StudyApp.models.data.FlashCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("flashcard")
public class FlashCardController {

    @Autowired
    private CardDeckRepository cardDeckRepository;

    @Autowired
    private FlashCardRepository flashCardRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value={"", "/index"})
    public String index(Model model) {

        model.addAttribute("title", "Memorization");

        return "flashcard/index";
    }

    @RequestMapping("view/{cardDeckId}")
    public String renderFlashCardDeck(Model model, @PathVariable int cardDeckId) {

        Optional optCardDeck = cardDeckRepository.findById(cardDeckId);
        if (optCardDeck.isPresent()) {
            CardDeck cardDeck = (CardDeck) optCardDeck.get();
            model.addAttribute("title", cardDeck.getName());
            model.addAttribute("flashCards", cardDeck.getFlashcards());
            return "flashcard/view";
        }

        return "flashcard/index";
    }

    @GetMapping("create")
    public String renderCreateCardDeckForm(Model model) {

        model.addAttribute("title", "New Card Deck");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute(new CardDeck());

        return "flashcard/create";
    }

    @PostMapping("create")
    public String processCreateCardDeckForm(@ModelAttribute @Valid CardDeck cardDeck, BindingResult bindingResult,
                                            Model model, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "New Card Deck");
            model.addAttribute("categories", categoryRepository.findAll());
            return "flashcard/create";
        }

        Enumeration requestParams = request.getParameterNames();
        Map<String, String> flashCardParams = new HashMap<>();
        while (requestParams.hasMoreElements()){
            String parameterName = (String) requestParams.nextElement();
            if (parameterName.contains("flashCard")) {
                flashCardParams.put(parameterName, request.getParameter(parameterName));
            }
        }

        if (flashCardParams.containsValue("") || flashCardParams.containsValue(null)) {
            model.addAttribute("title", "New Card Deck");
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("cardError", true);
            return "flashcard/create";
        }

        cardDeckRepository.save(cardDeck);

        List<FlashCard> flashCards = new ArrayList<>();
        for (int i = 0; i < (flashCardParams.size() / 2); i++) {
            String cardFront = flashCardParams.get("flashCardName" + i);
            String cardBack = flashCardParams.get("flashCardReverseSide" + i);
            FlashCard thisCard = new FlashCard(cardFront, cardBack, cardDeck);
            flashCards.add(thisCard);
        }

        flashCardRepository.saveAll(flashCards);
        return "redirect:index";
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
