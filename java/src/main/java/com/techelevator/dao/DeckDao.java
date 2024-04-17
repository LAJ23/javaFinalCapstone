package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.FlashCard;

import java.util.List;

public interface DeckDao {
    public List<Deck> getAllDecks(int id);
    public String getDeckName(int id);

    public List<FlashCard> getAllFlashcards(int id);

//    public boolean updatehighscore(Deck deck);
    public Deck getDeck(int deckId);
    public String getDeckHighScore(int id);
    public boolean updateDeck(Deck deck);
    public Deck addDeck(Deck deck);
    public boolean deleteDeckcards(int deckId);

    public FlashCard addblankcard(int deckId);


    public boolean deleteFlashcard(int id);

    public boolean updateFlashcard(int cardId, String question, String answer);

    public FlashCard addFlashcard(FlashCard card);
    public FlashCard getCardById(int cardId);
    public boolean deleteDeck(int deckId);



    public boolean updateDeck(int deckId, int color, String name);
}



