package com.techelevator.controller;
import com.techelevator.dao.DeckDao;
import com.techelevator.dao.JdbcDeckDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



        import com.techelevator.model.*;

import java.util.List;
//@PreAuthorize("isAuthenticated()")

@RestController
@CrossOrigin
public class FlashCardController  {
    private DeckDao deckDao;

    public FlashCardController(DeckDao deckDao) {
        this.deckDao = deckDao;
    }

    @RequestMapping(path = "/decks/{id}", method = RequestMethod.GET)
    public List<Deck> getAllDecks(@PathVariable int id) {
        List<Deck> decks = deckDao.getAllDecks(id);
        return decks;
    }

    @RequestMapping(path = "cards/{id}", method = RequestMethod.GET)
    public List<FlashCard> getAllCards(@PathVariable int deckID) {
        List<FlashCard> cards = deckDao.getAllFlashcards(deckID);
        return cards;
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<String> updateFlashcard(
            @PathVariable int cardId,
            @RequestParam String question,
            @RequestParam String answer) {

        boolean updateSuccessful = deckDao.updateFlashcard(cardId, question, answer);

        if (updateSuccessful) {
            return ResponseEntity.ok("Flashcard updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to update flashcard.");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlashcard(@PathVariable("id") int id) {
        boolean isDeleted = deckDao.deleteFlashcard(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/adddeck", method = RequestMethod.POST)
    public String addDeck(@RequestBody String name, int color, int creator_id) {
       deckDao.addDeck(name, color, creator_id);
        return "Deck Added";
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

    @PostMapping
    public ResponseEntity<Void> addFlashcard(@RequestBody FlashCard flashCard) {
        deckDao.addFlashcard(flashCard.getDeckId(), flashCard.getQuestion(), flashCard.getAnswer());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    }



