package com.techelevator.controller;
import com.techelevator.dao.DeckDao;
import org.springframework.web.bind.annotation.*;



        import com.techelevator.model.*;

import java.util.List;

@RestController
@CrossOrigin
public class FlashCardController  {
    private DeckDao deckDao;

    public FlashCardController(DeckDao deckDao) {
        this.deckDao = deckDao;
    }

    @RequestMapping(path = "Decks/{id}", method = RequestMethod.GET)
    public List<Deck> getAllDecks(@PathVariable int id) {
        List<Deck> decks = deckDao.getAllDecks(id);
        return decks;
    }

    @RequestMapping(path = "cards/{id}", method = RequestMethod.GET)
    public List<FlashCard> getAllCards(@PathVariable int deckID) {
        List<FlashCard> cards = deckDao.getAllFlashcards(deckID);
        return cards;
    }

}

