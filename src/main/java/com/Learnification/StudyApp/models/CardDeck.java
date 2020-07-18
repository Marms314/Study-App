package com.Learnification.StudyApp.models;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CardDeck extends AbstractEntity {

    private List<Question> questions = new ArrayList<>();

    private Category category;

    public CardDeck() {}

    public CardDeck(List<Question> questions, Category category) {
        super();
        this.questions = questions;
        this.category = category;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
