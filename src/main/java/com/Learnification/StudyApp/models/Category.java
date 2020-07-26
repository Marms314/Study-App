package com.Learnification.StudyApp.models;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Category extends AbstractEntity {

    @ManyToMany(mappedBy = "categories")
    private List<Quiz> quizzes = new ArrayList<>();

    @ManyToMany(mappedBy = "categories")
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
