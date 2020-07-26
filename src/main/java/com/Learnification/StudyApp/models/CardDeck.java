package com.Learnification.StudyApp.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
public class CardDeck extends AbstractEntity {

    @OneToMany(mappedBy = "cardDeck")
    private List<FlashCard> flashcards = new ArrayList<>();

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    public CardDeck() {}

    public CardDeck(List<FlashCard> flashCards, List<Category> categories) {
        super();
        this.flashcards = flashCards;
        this.categories = categories;
    }

    public void addFlashCard(FlashCard flashCard) {
        this.flashcards.add(flashCard);
    }

    public List<FlashCard> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(List<FlashCard> flashcards) {
        this.flashcards = flashcards;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
