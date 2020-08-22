package com.Learnification.StudyApp.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Quiz extends AbstractEntity {

    @OneToMany(mappedBy = "quiz")
    private List<Question> questions = new ArrayList<>();

    @NotBlank(message = "Field cannot be blank.")
    @Size(min = 1, max = 100, message = "Field must be between 1 and 100 characters.")
    private String description;

    @ManyToMany(mappedBy = "quizzes")
    private Set<Category> categories = new HashSet<>();

    public Quiz() {}

    public Quiz(List<Question> questions, String description) {
        super();
        this.questions = questions;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
        category.addQuiz(this);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
        category.getQuizzes().remove(this);
    }

    public void removeAllCategories() {
        for (Category category : this.categories) {
            category.removeQuiz(this);
        }
        this.categories.removeAll(this.categories);
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public boolean contains(String textToCompare) {
        String quizInfo = this.toString();
        return quizInfo.toLowerCase().contains(textToCompare.toLowerCase());
    }

    @Override
    public String toString() {
        return super.toString() + " " + description;
    }
}
