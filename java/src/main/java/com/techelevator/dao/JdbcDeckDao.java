package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Deck;
import com.techelevator.model.FlashCard;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

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
    @CrossOrigin
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
        String sql = "SELECT card_id, deck_id, question, answer FROM flashcard WHERE deck_id = ?";
        ;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {

            flashCards.add(mapRowToFlash(results));

        }
        return flashCards;
    }

    @Override
    public String getDeckName(int id) {
        String sql = "SELECT name FROM deck WHERE deck_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()) {
            return results.getString("name"); // Extract the name column from the first row of the result set
        } else {
            return null; // Return null if no results found (or you could throw an exception or return a default value)
        }
    }

    @Override
    public String getDeckHighScore(int id) {
        String sql = "SELECT high_score FROM deck WHERE deck_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()) {
            return results.getString("high_score"); // Extract the name column from the first row of the result set
        } else {
            return null; // Return null if no results found (or you could throw an exception or return a default value)
        }
    }

    @Override
    public Deck getDeck(int deckId) {
        String sql = "SELECT * FROM deck WHERE deck_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, deckId);
        if (results.next()) {  // Ensure there is at least one row
            return mapRowToDeck(results);
        } else {
            return null;  // Return null or handle it based on your application's requirement
        }
    }

    @Override
    public boolean updateDeck(Deck deck) {
        String sql = "UPDATE deck SET name = ?, high_score = ?, color = ? WHERE deck_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, deck.getDeckName(), deck.getHighScore(), deck.getColor(), deck.getDeckId());
        return rowsAffected > 0;
    }

    @Override
    public Deck addDeck(Deck deck) {
        Deck newDeck = null;
        String sql = "INSERT INTO deck (name, color, creator_id)" +
                "VALUES (?,?, (SELECT user_id FROM users WHERE user_id = ?)) RETURNING deck_id";
        try {
            int newDeckId = jdbcTemplate.queryForObject(sql, int.class, deck.getDeckName(),  deck.getColor(), deck.getUserID());
            newDeck = getDeck(newDeckId);
            return newDeck;

        }
        catch(CannotGetJdbcConnectionException ex){
            throw new DaoException("Cannot connect to the database", ex);
        }
        catch(DataIntegrityViolationException ex) {
            throw new DaoException("Data integrity violation", ex);
        }
        catch(BadSqlGrammarException ex) {
            throw new DaoException("Bad SQL Grammar", ex);
        }
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
    public FlashCard addFlashcard(FlashCard card) {
        FlashCard newCard = null;
        String sql = "INSERT INTO flashcard (deck_id, question, answer) VALUES (?, ?, ?)";
        try {
            int newCardId = jdbcTemplate.queryForObject(sql, int.class, card.getDeckId(), card.getQuestion(), card.getAnswer());
            newCard = getCardById(newCardId);
            return newCard;
        }
        catch(CannotGetJdbcConnectionException ex){
            throw new DaoException("Cannot connect to the database", ex);
        }
        catch(DataIntegrityViolationException ex) {
            throw new DaoException("Data integrity violation", ex);
        }
        catch(BadSqlGrammarException ex) {
            throw new DaoException("Bad SQL Grammar", ex);
        }
    }

    @Override
    public FlashCard getCardById(int cardId) {
        FlashCard card = null;
        String sql = "SELECT * " +
                "FROM flashcard " +
                "WHERE card_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cardId);
        if (results.next()) {
            card = mapRowToFlash(results);
        }
        return card;
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
