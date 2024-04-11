package com.techelevator.model;

public class FlashCard {

  private int card_id;

  private int deck_id;
    private String question;
    private String answer;



    public FlashCard(){};

    public FlashCard(int card_id, int deck_id, String question, String answer){
     this.card_id = card_id;
     this.deck_id = deck_id;
     this.question = question;
     this.answer = answer;
    }

    public int getCardId() {
      return card_id;
    }

    public void setCardId(int id) {
      this.card_id = card_id;
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
    return deck_id;
    }

    public void setDeckId(int deckId) {
    this.deck_id = deck_id;
    }
}
