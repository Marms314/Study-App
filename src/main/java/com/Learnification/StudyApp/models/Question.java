package com.Learnification.StudyApp.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class Question extends AbstractEntity {

    @ManyToOne
    private Quiz quiz;

    @NotBlank(message = "Field cannot be blank.")
    @Size(min = 1, max = 100, message = "Field must be between 1 and 100 characters.")
    private String correctAnswer;

    @NotBlank(message = "Field cannot be blank.")
    @Size(min = 1, max = 100, message = "Field must be between 1 and 100 characters.")
    private String wrongAnswer;

    public Question() {}

    public Question(Quiz quiz, String correctAnswer, String wrongAnswer) {
        this.quiz = quiz;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer = wrongAnswer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(String wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }
}
