package com.Learnification.StudyApp;

import com.Learnification.StudyApp.models.CardDeck;
import com.Learnification.StudyApp.models.Category;
import com.Learnification.StudyApp.models.Quiz;
import com.Learnification.StudyApp.models.data.CardDeckRepository;
import com.Learnification.StudyApp.models.data.CategoryRepository;
import com.Learnification.StudyApp.models.data.QuizRepository;

import java.util.ArrayList;

public class DatabaseSearch {

    public ArrayList<Quiz> findByText(QuizRepository quizRepository, String searchText) {
        ArrayList<Quiz> allQuizzes = (ArrayList<Quiz>) quizRepository.findAll();
        ArrayList<Quiz> matchingQuizzes = new ArrayList<>();

        for (Quiz currentQuiz : allQuizzes) {
            if (currentQuiz.contains(searchText)) {
                matchingQuizzes.add(currentQuiz);
            }
        }

        return matchingQuizzes;
    }

    public ArrayList<CardDeck> findByText(CardDeckRepository cardDeckRepository, String searchText) {
        ArrayList<CardDeck> allCardDecks = (ArrayList<CardDeck>) cardDeckRepository.findAll();
        ArrayList<CardDeck> matchingCardDecks = new ArrayList<>();

        for (CardDeck currentCardDeck : allCardDecks) {
            if (currentCardDeck.contains(searchText)) {
                matchingCardDecks.add(currentCardDeck);
            }
        }

        return matchingCardDecks;
    }

    public ArrayList<Category> findByText(CategoryRepository categoryRepository, String searchText) {
        ArrayList<Category> allCategories = (ArrayList<Category>) categoryRepository.findAll();
        ArrayList<Category> matchingCategories = new ArrayList<>();

        for (Category currentCategory : allCategories) {
            if (currentCategory.contains(searchText)) {
                matchingCategories.add(currentCategory);
            }
        }

        return matchingCategories;
    }
}
