package com.Learnification.StudyApp.models;

import java.util.HashMap;

public class Question extends AbstractEntity {

    private String quiz;

    private HashMap<String, Boolean> answers;

    public Question() {}

    public Question(String quiz, HashMap<String, Boolean> answers) {
        super();
        this.quiz = quiz;
        this.answers = answers;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public HashMap<String, Boolean> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<String, Boolean> answers) {
        this.answers = answers;
    }
}
