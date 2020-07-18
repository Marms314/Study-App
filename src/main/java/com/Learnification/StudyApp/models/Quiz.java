package com.Learnification.StudyApp.models;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Quiz extends AbstractEntity {

    private List<Question> questions;

    public Quiz() {}

    public Quiz(List<Question> questions) {
        super();
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
