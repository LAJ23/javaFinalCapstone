package com.techelevator.dao;


import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.Flashcard;

import java.util.List;

public interface FlashcardDao {

    List<Flashcard> listCards();

    Flashcard getCardByCardId(int card_id);

    Flashcard getCardByDeckId(int deck_id);

    Flashcard createCard(Flashcard flashcard);

    Flashcard updateCard(int card_id);

    Flashcard deleteCardById(int card_id);
}
