package com.Learnification.StudyApp.controllers;

import com.Learnification.StudyApp.models.CardDeck;
import com.Learnification.StudyApp.models.FlashCard;
import com.Learnification.StudyApp.models.Question;
import com.Learnification.StudyApp.models.Quiz;
import com.Learnification.StudyApp.models.data.CardDeckRepository;
import com.Learnification.StudyApp.models.data.FlashCardRepository;
import com.Learnification.StudyApp.models.data.QuestionRepository;
import com.Learnification.StudyApp.models.data.QuizRepository;
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

    @RequestMapping("")
    public String index(Model model) {
        List<CardDeck> allCardDecks = (ArrayList<CardDeck>) cardDeckRepository.findAll();
        ArrayList<CardDeck> cardDecksToReturn = new ArrayList<>();

        while (cardDecksToReturn.size() < 3) {
            int randomIndex = (int) (Math.random() * (allCardDecks.size() - 1));
            CardDeck deckToCheck = allCardDecks.get(randomIndex);

            if (!cardDecksToReturn.contains(deckToCheck)) {
                cardDecksToReturn.add(deckToCheck);
            }
        }

        List<Quiz> allQuizzes = (ArrayList<Quiz>) quizRepository.findAll();
        ArrayList<Quiz> quizzesToReturn = new ArrayList<>();

        while (quizzesToReturn.size() < 3) {
            int randomIndex = (int) (Math.random() * (allQuizzes.size() - 1));
            Quiz quizToCheck = allQuizzes.get(randomIndex);

            if (!quizzesToReturn.contains(quizToCheck)) {
                quizzesToReturn.add(quizToCheck);
            }
        }

        boolean cardDecksExist = cardDeckRepository.count() != 0;
        boolean quizzesExist = quizRepository.count() != 0;
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
        for (int i = 0; i < 11; i++) {
            String name = "Multiples of " + i;
            String description = " your knowledge of multiplication using the number " + i;

            Quiz quiz = new Quiz();
            CardDeck cardDeck = new CardDeck();

            quiz.setName(name);
            quiz.setDescription("Test" + description);
            cardDeck.setName(name);
            cardDeck.setDescription("Improve" + description);

            quizRepository.save(quiz);
            cardDeckRepository.save(cardDeck);

            List<Question> questions = new ArrayList<> ();
            List<FlashCard> flashCards = new ArrayList<> ();
            int randomNumber = 1 + (int)(Math.random() * ((5 - 1) + 1));

            for (int j = 0; j < 11; j++) {
                String equation = i + " x " + j + " = ";
                String correctAnswer = String.valueOf(i * j);
                String wrongAnswer = String.valueOf(i * (j + randomNumber));

                Question thisQuestion = new Question(equation, correctAnswer, wrongAnswer, quiz);
                FlashCard thisCard = new FlashCard(equation, correctAnswer, cardDeck);
                flashCards.add(thisCard);
                questions.add(thisQuestion);
            }

            flashCardRepository.saveAll(flashCards);
            questionRepository.saveAll(questions);
        }

        model.addAttribute("dummyDataAdded", true);

        return "index";
    }

}