package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.FlashCard;

import java.util.List;

public interface DeckDao {
    public List<Deck> getAllDecks(int id);

    public List<FlashCard> getAllFlashcards(int id);


public interface DeckDao {
    List<Deck> listDecks();

    Deck getDeckById(int deck_id);

    Deck createDeck(Deck deck);
    Deck updateDeck(int deck_id);
    Deck deleteDeck(int deck_id);


}
