package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlashCard {

  private int cardId;
@JsonProperty("deck_id")
  private int deckId;
    private String question;
    private String answer;



    public FlashCard(){};

    public FlashCard(int cardId, int deckId, String question, String answer){
     this.cardId = cardId;
     this.deckId = deckId;
     this.question = question;
     this.answer = answer;
    }

    public int getCardId() {
      return cardId;
    }

    public void setCardId(int cardId) {
      this.cardId = cardId;
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

    public int getDeckId() {
    return deckId;
    }

    public void setDeckId(int deckId) {
    this.deckId = deckId;
    }
}
