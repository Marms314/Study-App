package com.Learnification.StudyApp.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class FlashCard extends AbstractEntity {

    @NotBlank(message = "Field cannot be blank.")
    @Size(min = 1, max = 100, message = "Field must be between 1 and 100 characters.")
    private String reverseSide;

    @NotBlank(message = "Field cannot be blank.")
    @ManyToOne
    private CardDeck cardDeck;

    public FlashCard() {}

    public FlashCard(String reverseSide,CardDeck cardDeck) {
        this.reverseSide = reverseSide;
        this.cardDeck = cardDeck;
    }

    public String getReverseSide() {
        return reverseSide;
    }

    public void setReverseSide(String reverseSide) {
        this.reverseSide = reverseSide;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }
}
