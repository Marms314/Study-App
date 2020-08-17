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
public class CardDeck extends AbstractEntity {

    @OneToMany(mappedBy = "cardDeck")
    private List<FlashCard> flashcards = new ArrayList<>();

    @NotBlank(message = "Field cannot be blank.")
    @Size(min = 1, max = 100, message = "Field must be between 1 and 100 characters.")
    private String description;

    @ManyToMany(mappedBy = "cardDecks")
    private Set<Category> categories = new HashSet<>();

    public CardDeck() {}

    public CardDeck(List<FlashCard> flashCards, String description) {
        super();
        this.flashcards = flashCards;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
        category.addCardDeck(this);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
        category.getCardDecks().remove(this);
    }

    public void removeAllCategories() {
        for (Category category : this.categories) {
            category.removeCardDeck(this);
        }
        this.categories.removeAll(this.categories);
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
