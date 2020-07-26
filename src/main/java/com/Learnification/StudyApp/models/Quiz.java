package com.Learnification.StudyApp.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Quiz extends AbstractEntity {

    @OneToMany(mappedBy = "quiz")
    private List<Question> questions = new ArrayList<>();

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    public Quiz() {}

    public Quiz(List<Question> questions, List<Category> categories) {
        super();
        this.questions = questions;
        this.categories = categories;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
