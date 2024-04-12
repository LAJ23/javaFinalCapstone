package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.FlashCard;

import java.util.List;

public interface DeckDao {
    public List<Deck> getAllDecks(int id);

    public List<FlashCard> getAllFlashcards(int id);

    public boolean deleteFlashcard(int id);

    public boolean updateFlashcard(int cardId, String question, String answer);

    public void addFlashcard(int deckId, String question, String answer);

    public boolean deleteDeck(int deckId);

    public void addDeck(String name, int color, int creator_id);

    public boolean updateDeck(int deckId, int color, String name);
}



