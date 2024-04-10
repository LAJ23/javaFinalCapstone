package com.techelevator.model;

public class FlashCard {
    private int cardId;
    private int deckId;
    private String question;
    private String answer;

    public FlashCard(int cardId, int deckId, String question, String answer) {
        this.cardId = cardId;
        this.deckId = deckId;
        this.question = question;
        this.answer = answer;
    }
    public FlashCard() {
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
