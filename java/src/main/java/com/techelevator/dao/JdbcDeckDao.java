package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.FlashCard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcDeckDao implements DeckDao {
    private static JdbcTemplate jdbcTemplate;

    public void DeckDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void DeckDao() {

    }

    @Override
    public List<Deck> getAllDecks(int id) {
        List<Deck> Decks = new ArrayList<>();
        String sql = "SELECT * FROM deck WHERE user id = ? || user id = 2";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Deck deck = mapRowToDeck(results);
            Decks.add(deck);
        }
        return Decks;

    }

    @Override
    public List<FlashCard> getAllFlashcards(int id) {
        List<FlashCard> flashCards = new ArrayList<>();
        String sql = "SELECT * FROM flashcard WHERE deck_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            FlashCard flashCard = mapRowToFlash(results);
            flashCards.add(flashCard);

        }
        return flashCards;
    }

    private Deck mapRowToDeck(SqlRowSet rowset) {
        Deck deck = new Deck();
        deck.setColor(rowset.getInt("color"));
        deck.setDeckId(rowset.getInt("deck_id"));
        deck.setDeckName(rowset.getString("name"));
        deck.setHighScore(rowset.getInt("high_score"));
        deck.setUserID(rowset.getInt("creator_id"));
        return deck;
    }

    private FlashCard mapRowToFlash(SqlRowSet rowset) {
        FlashCard flashCard = new FlashCard();
        flashCard.setAnswer(rowset.getString("answer"));
        flashCard.setCardId(rowset.getInt("card_id"));
        flashCard.setQuestion(rowset.getString("question"));
        flashCard.setDeckId(rowset.getInt("deck_id"));

        return flashCard;
    }
}
