package com.Learnification.StudyApp.controllers;

import com.Learnification.StudyApp.DummyDatabaseData;
import com.Learnification.StudyApp.models.CardDeck;
import com.Learnification.StudyApp.models.Quiz;
import com.Learnification.StudyApp.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CardDeckRepository cardDeckRepository;

    @Autowired
    private FlashCardRepository flashCardRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("")
    public String index(Model model) {
        boolean cardDecksExist = cardDeckRepository.count() != 0;
        List<CardDeck> allCardDecks = (ArrayList<CardDeck>) cardDeckRepository.findAll();
        ArrayList<CardDeck> cardDecksToReturn = new ArrayList<>();

        if (cardDecksExist) {
            while (cardDecksToReturn.size() < 3) {
                int randomIndex = (int) (Math.random() * (allCardDecks.size() - 1));
                CardDeck deckToCheck = allCardDecks.get(randomIndex);

                if (!cardDecksToReturn.contains(deckToCheck)) {
                    cardDecksToReturn.add(deckToCheck);
                }
            }
        }

        boolean quizzesExist = quizRepository.count() != 0;
        List<Quiz> allQuizzes = (ArrayList<Quiz>) quizRepository.findAll();
        ArrayList<Quiz> quizzesToReturn = new ArrayList<>();

        if (quizzesExist) {
            while (quizzesToReturn.size() < 3) {
                int randomIndex = (int) (Math.random() * (allQuizzes.size() - 1));
                Quiz quizToCheck = allQuizzes.get(randomIndex);

                if (!quizzesToReturn.contains(quizToCheck)) {
                    quizzesToReturn.add(quizToCheck);
                }
            }
        }

        model.addAttribute("cardDecksExist", cardDecksExist);
        model.addAttribute("quizzesExist", quizzesExist);
        model.addAttribute("title", "Let's get our study on.");
        model.addAttribute("cardDecks", cardDecksToReturn);
        model.addAttribute("quizzes", quizzesToReturn);
        return "index";
    }

    //Adds quizzes and card decks of multiplication to allow the app to be tested quickly
    @RequestMapping("add-dummy-data")
    public String dummyData(Model model) {
        DummyDatabaseData dummyDatabaseData = new DummyDatabaseData();
        dummyDatabaseData.addData(quizRepository, cardDeckRepository, questionRepository, flashCardRepository, categoryRepository);
        return "redirect:";
    }

}