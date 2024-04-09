package com.techelevator.controller;

import com.techelevator.model.FlashCardQuestions;
import com.techelevator.service.FlashCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// *************************** This code is not functional yet. 



@RestController
@CrossOrigin
public class FlashcardController {

  private final FlashCardService flashCardService;

  @Autowired
  public void FlashCardController(FlashCardService flashCardService) {
      this.flashCardService = flashCardService;
  }

 @GetMapping
    public ResponseEntity<List<FlashCardQuestions>> getAllFlashCards() {
        List<FlashCardQuestions> flashCards = flashCardService.findAll();
        return new ResponseEntity<>(flashCards, HttpStatus.OK);
}

@GetMapping("/{id}")
public ResponseEntity<FlashCardQuestions> getFlashCardById(@PathVariable int id) {
    FlashCardQuestions flashCard = flashCardService.findById(id);
    if(flashCard == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(flashCard, HttpStatus.OK);
}

@PostMapping
    public ResponseEntity<FlashCardQuestions> addFlashCard(@RequestBody FlashCardQuestions flashCard) {
        FlashCardQuestions newFlashCard = flashCardService.save(flashCard);
        return new ResponseEntity<>(newFlashCard, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlashCardQuestions> updateFlashCard(@PathVariable int id, @RequestBody FlashCardQuestions flashCard) {
        FlashCardQuestions updatedFlashCard = flashCardService.update(id, flashCard);
        if(updatedFlashCard == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedFlashCard, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlashCard(@PathVariable int id) {
        boolean isDeleted = flashCardService.delete(id);
        if(!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
};