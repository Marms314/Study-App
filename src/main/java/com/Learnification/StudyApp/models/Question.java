package com.Learnification.StudyApp.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.HashMap;

@Entity
public class Question extends AbstractEntity {

    @ManyToOne
    private Quiz quiz;

    private HashMap<String, Boolean> answers = new HashMap<>();

    public Question() {}

    public Question(Quiz quiz, HashMap<String, Boolean> answers) {
        super();
        this.quiz = quiz;
        this.answers = answers;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public HashMap<String, Boolean> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<String, Boolean> answers) {
        this.answers = answers;
    }
}
