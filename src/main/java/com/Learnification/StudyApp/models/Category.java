package com.Learnification.StudyApp.models;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends AbstractEntity {

    private List<Quiz> quizzes = new ArrayList<>();

    private List<CardDeck> cardDecks = new ArrayList<>();

    public Category() {}

    public Category(List<Quiz> quizzes, List<CardDeck> cardDecks) {
        super();
        this.quizzes = quizzes;
        this.cardDecks = cardDecks;
    }

    public void addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
    }

    public void addCardDeck(Quiz cardDeck) {
        this.quizzes.add(cardDeck);
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public List<CardDeck> getCardDecks() {
        return cardDecks;
    }

    public void setCardDecks(List<CardDeck> cardDecks) {
        this.cardDecks = cardDecks;
    }
}
