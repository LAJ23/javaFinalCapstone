package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.FlashCard;

import java.util.List;

public interface DeckDao {
    public List<Deck> getAllDecks(int id);

    public List<FlashCard> getAllFlashcards(int id);
}



