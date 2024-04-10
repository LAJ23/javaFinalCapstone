package com.techelevator.model;

public class Flashcards {
  private int card_id;

  private int deck_id;
    private String question;
    private String answer;


    public Flashcards(){};

    public Flashcards(int card_id, int deck_id, String question, String answer){
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

    public int getDeck_id() {
    return deck_id;
    }

    public void setDeck_id(int deck_id) {
    this.deck_id = deck_id;
    }
}
