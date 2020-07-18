package com.Learnification.StudyApp.models;

import javax.persistence.Entity;

@Entity
public class FlashCard extends AbstractEntity {

    private String reverseSide;

    private String cardDeck;

    public FlashCard() {}

    public FlashCard(String reverseSide, String cardDeck) {
        this.reverseSide = reverseSide;
        this.cardDeck = cardDeck;
    }

    public String getReverseSide() {
        return reverseSide;
    }

    public void setReverseSide(String reverseSide) {
        this.reverseSide = reverseSide;
    }

    public String getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(String cardDeck) {
        this.cardDeck = cardDeck;
    }
}
