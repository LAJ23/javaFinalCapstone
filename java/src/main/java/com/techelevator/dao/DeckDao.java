package com.techelevator.dao;
import java.util.List;
import com.techelevator.model.Deck;

public interface DeckDao {
    List<Deck> listDecks();

    Deck getDeckById(int deck_id);

    Deck createDeck(Deck deck);
    Deck updateDeck(int deck_id);
    Deck deleteDeck(int deck_id);


}
