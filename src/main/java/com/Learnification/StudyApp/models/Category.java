package com.Learnification.StudyApp.models;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Category extends AbstractEntity {

    @ManyToMany
    private Set<Quiz> quizzes = new HashSet<>();

    @ManyToMany
    private Set<CardDeck> cardDecks = new HashSet<>();

    public Category() {}

    public Category(Set<Quiz> quizzes, Set<CardDeck> cardDecks) {
        super();
        this.quizzes = quizzes;
        this.cardDecks = cardDecks;
    }

    public void addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
    }

    public void removeQuiz(Quiz quiz) {
        this.quizzes.remove(quiz);
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void addCardDeck(CardDeck cardDeck) {
        this.cardDecks.add(cardDeck);
    }

    public void removeCardDeck(CardDeck cardDeck) {
        this.cardDecks.remove(cardDeck);
    }

    public Set<CardDeck> getCardDecks() {
        return cardDecks;
    }

    public void setCardDecks(Set<CardDeck> cardDecks) {
        this.cardDecks = cardDecks;
    }

    public boolean contains(String textToCompare) {
        String categoryInfo = this.toString();
        return categoryInfo.toLowerCase().contains(textToCompare.toLowerCase());
    }
}
