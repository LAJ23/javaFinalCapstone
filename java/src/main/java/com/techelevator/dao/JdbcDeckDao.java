package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.FlashCard;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcDeckDao implements DeckDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcDeckDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void DeckDao() {

    }

    @Override
    public List<Deck> getAllDecks(int id) {
        List<Deck> decks = new ArrayList<>();
        String sql = "SELECT * FROM deck WHERE creator_id = ? OR creator_id = 2";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id); // Pass the id as parameter
        while (results.next()) {
            Deck deck = mapRowToDeck(results);
            decks.add(deck);
        }
        return decks;
    }

    @Override
    public List<FlashCard> getAllFlashcards(int id) {
        List<FlashCard> flashCards = new ArrayList<>();
        String sql = "SELECT * FROM flashcard WHERE deck_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            FlashCard flashCard = mapRowToFlash(results);
            flashCards.add(flashCard);

        }
        return flashCards;
    }

    @Override
    public boolean updateFlashcard(int cardId, String question, String answer) {
        String sql = "UPDATE flashcard SET question = ?, answer = ? WHERE card_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, question, answer, cardId);
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteFlashcard(int Id) {
        String sql = "DELETE FROM flashcard WHERE card_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, Id);
        return rowsAffected > 0;
    }

    @Override
    public void addFlashcard(int deckId, String question, String answer) {
        String sql = "INSERT INTO flashcard (deck_id, question, answer) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, deckId, question, answer);
        } catch (DataAccessException e) {
            throw new EmptyResultDataAccessException("Failed to insert flashcard into the database.", 1);
        }
    }

    @Override
    @Transactional
    public boolean deleteDeck(int deckId) {
        String sql = "DELETE FROM flashcard WHERE deck_id = ?";
        jdbcTemplate.update(sql, deckId);
        String deleteDeckSql = "DELETE FROM deck WHERE deck_id = ?";
        int rowsAffected = jdbcTemplate.update(deleteDeckSql, deckId);

        return rowsAffected > 0;
    }

    public boolean updateDeck(int deckId, int color, String name) {
        String sql = "UPDATE deck SET color = ?, name = ? WHERE deck_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, color, name, deckId);
        return rowsAffected > 0;
    }

    @Override
    public void addDeck(String name, int color) {
        String sql = "INSERT INTO deck (name, color) VALUES (?, ?)";
        jdbcTemplate.update(sql, name, color);
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
