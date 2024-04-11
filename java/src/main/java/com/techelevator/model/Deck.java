package com.techelevator.model;

public class Deck {
    private int deckId;
    private String deckName;
    private int highScore;
    private int color;
    private int userID;


    public Deck() {
    }

    public Deck(int deckId, String deckName, int highScore, int color, int userID) {
        this.deckId = deckId;
        this.deckName = deckName;
        this.highScore = highScore;
        this.color = color;
        this.userID = userID;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
