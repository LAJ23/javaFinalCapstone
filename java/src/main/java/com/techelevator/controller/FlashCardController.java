package com.techelevator.controller;
import com.techelevator.dao.DeckDao;
import com.techelevator.dao.JdbcDeckDao;
import com.techelevator.dao.UserDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



        import com.techelevator.model.*;

import java.security.Principal;
import java.util.List;
//@PreAuthorize("isAuthenticated()")

@RestController
@CrossOrigin
public class FlashCardController {
    private DeckDao deckDao;
    private UserDao userDao;

    public FlashCardController(DeckDao deckDao, UserDao userDao) {
        this.deckDao = deckDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/decks/{id}", method = RequestMethod.GET)
    public List<Deck> getAllDecks(@PathVariable int id) {
        List<Deck> decks = deckDao.getAllDecks(id);
        return decks;
    }

    @RequestMapping(path = "/cards/{id}", method = RequestMethod.GET)
    public List<FlashCard> getAllCards(@PathVariable int id) {
        List<FlashCard> cards = deckDao.getAllFlashcards(id);
        return cards;
    }

    @RequestMapping(path = "/deckName/{id}", method = RequestMethod.GET)
    public String getDeckName(@PathVariable int id) {
        String name = deckDao.getDeckName(id);
        return name;
    }

    @RequestMapping(path = "deck/{deckId}", method = RequestMethod.GET)
    public Deck getDeck(@PathVariable int deckId) {
        Deck deck = deckDao.getDeck(deckId);
        return deck;
    }

    @RequestMapping(path = "/updateDeck", method = RequestMethod.POST)
    public ResponseEntity<?> updateDeck(@RequestBody Deck deck) {
        System.out.println("Received update request for deck with ID: " + deck.getDeckId());
        System.out.println("Name: " + deck.getDeckName());
        System.out.println("HighScore: " + deck.getHighScore());
        System.out.println("Color: " + deck.getColor());
        System.out.println("Creator ID: " + deck.getUserID());

        try {
            boolean updateSuccessful = deckDao.updateDeck(deck);
            if (updateSuccessful) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("Update failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating deck: " + e.getMessage());
        }
    }

    @RequestMapping(path = "/adddeck", method = RequestMethod.POST)
    public Deck addDeck(@RequestBody Deck deck) {

        Deck newDeck = null;
        newDeck = deckDao.addDeck(deck);
        return newDeck;
    }


    @RequestMapping(path = "/score/{id}", method = RequestMethod.GET)
    public String getHighScore(@PathVariable int id) {
        String score = deckDao.getDeckHighScore(id);
        return score;
    }
//--------------------------------------------------------------------------------------------------------


    @PutMapping("/deck/{cardId}")
    public ResponseEntity<String> updateFlashcard(
            @PathVariable int cardId,
            @RequestParam String question,
            @RequestParam String answer) {

        try {
            boolean updateSuccessful = deckDao.updateFlashcard(cardId, question, answer);

            if (updateSuccessful) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("Failed to update flashcard.");
            }
        }
        catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating card: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlashcard(@PathVariable("id") int card_id) {
        boolean isDeleted = deckDao.deleteFlashcard(card_id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{deckId}")
    public ResponseEntity<?> updateDeck(@PathVariable("deckId") int deckId,
                                        @RequestParam("color") int color,
                                        @RequestParam("name") String name) {
        boolean isUpdated = deckDao.updateDeck(deckId, color, name);
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{deckId}")
    public ResponseEntity<?> deleteDeck(@PathVariable("deckId") int deckId) {
        boolean isDeleted = deckDao.deleteDeck(deckId);
        if (isDeleted) {
            return ResponseEntity.ok().build(); // Successfully deleted
        } else {
            return ResponseEntity.notFound().build(); // Deck not found
        }
    }

    @RequestMapping(path = "/savecard", method = RequestMethod.POST)
    public FlashCard saveFlashcard(@RequestBody FlashCard card) {
        FlashCard newCard = null;
        newCard = deckDao.addFlashcard(card);
        return newCard;
    }
}
//    @PostMapping
//    public ResponseEntity<Void> addFlashcard(@RequestBody FlashCard flashCard) {
//        deckDao.addFlashcard(flashCard.getDeckId(), flashCard.getQuestion(), flashCard.getAnswer());
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//    }



