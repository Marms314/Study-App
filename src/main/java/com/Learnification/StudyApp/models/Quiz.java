package com.Learnification.StudyApp.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Quiz extends AbstractEntity {

    @OneToMany(mappedBy = "quiz")
    private List<Question> questions = new ArrayList<>();

    @ManyToOne
    private Category category;

    private int scoreValue = questions.size();

    public Quiz() {}

    public Quiz(List<Question> questions, Category category) {
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

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }
}
