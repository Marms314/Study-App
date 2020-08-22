package com.Learnification.StudyApp.controllers;

import com.Learnification.StudyApp.DatabaseSearch;
import com.Learnification.StudyApp.DummyDatabaseData;
import com.Learnification.StudyApp.models.CardDeck;
import com.Learnification.StudyApp.models.Category;
import com.Learnification.StudyApp.models.Quiz;
import com.Learnification.StudyApp.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("search")
    public String renderSearchForm(Model model) {

        model.addAttribute("title", "Search");
        return "search";
    }

    @PostMapping("search")
    public String processSearchForm(@RequestParam String searchText, Model model, @RequestParam(required = false) String quizzes,
                                    @RequestParam(required = false) String cardDecks, @RequestParam(required = false) String categories,
                                    HttpServletRequest request) {

        if (searchText.isBlank() || searchText.isEmpty()) {
            model.addAttribute("textError", true);
            model.addAttribute("title", "Search");
            return "search";
        }

        boolean quizzesWereRequested = false;
        boolean cardDecksWereRequested = false;
        boolean categoriesWereRequested = false;
        DatabaseSearch databaseSearch = new DatabaseSearch();

        if (request.getParameterMap().containsKey("quizzes")) {
            quizzesWereRequested = true;
            ArrayList<Quiz> matchingQuizzes = databaseSearch.findByText(quizRepository, searchText);
            boolean hasNoMatchingQuizzes = matchingQuizzes.isEmpty();

            model.addAttribute("matchingQuizzes", matchingQuizzes);
            model.addAttribute("hasNoMatchingQuizzes", hasNoMatchingQuizzes);
        }

        if (request.getParameterMap().containsKey("cardDecks")) {
            cardDecksWereRequested = true;
            ArrayList<CardDeck> matchingCardDecks = databaseSearch.findByText(cardDeckRepository, searchText);
            boolean hasNoMatchingCardDecks = matchingCardDecks.isEmpty();

            model.addAttribute("matchingCardDecks", matchingCardDecks);
            model.addAttribute("hasNoMatchingCardDecks", hasNoMatchingCardDecks);
        }

        if (request.getParameterMap().containsKey("categories")) {
            categoriesWereRequested = true;
            ArrayList<Category> matchingCategories = databaseSearch.findByText(categoryRepository, searchText);
            boolean hasNoMatchingCategories = matchingCategories.isEmpty();

            model.addAttribute("matchingCategories", matchingCategories);
            model.addAttribute("hasNoMatchingCategories", hasNoMatchingCategories);
        }

        if ( !(quizzesWereRequested || cardDecksWereRequested || categoriesWereRequested) ) {
            model.addAttribute("searchLocationError", true);
            model.addAttribute("title", "Search");
            return "search";
        }

        model.addAttribute("quizzesWereRequested", quizzesWereRequested);
        model.addAttribute("cardDecksWereRequested", cardDecksWereRequested);
        model.addAttribute("categoriesWereRequested", categoriesWereRequested);
        model.addAttribute("searchWasDone", true);
        model.addAttribute("title", "Search for " + searchText);
        return "search";
    }

}