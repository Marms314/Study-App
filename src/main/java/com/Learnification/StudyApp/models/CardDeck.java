package com.Learnification.StudyApp.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Entity
public class CardDeck extends AbstractEntity {

    @NotBlank(message = "Field cannot be blank.")
    @OneToMany(mappedBy = "cardDeck")
    private List<FlashCard> flashcards = new ArrayList<>();

    @NotBlank(message = "Field cannot be blank.")
    @ManyToOne
    private Category category;

    public CardDeck() {}

    public CardDeck(List<FlashCard> flashCards, Category category) {
        super();
        this.flashcards = flashCards;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
