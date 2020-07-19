package com.Learnification.StudyApp.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class FlashCard extends AbstractEntity {

    private String reverseSide;

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
