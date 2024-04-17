package com.techelevator.dao;
import com.techelevator.model.FlashCard;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlashCardRowMapper implements RowMapper<FlashCard> {

    @Override
    public FlashCard mapRow(ResultSet rs, int rowNum) throws SQLException {
        FlashCard flashCard = new FlashCard();
        flashCard.setCardId(rs.getInt("card_id"));
        flashCard.setDeckId(rs.getInt("deck_id"));
        flashCard.setQuestion(rs.getString("question"));
        flashCard.setAnswer(rs.getString("answer"));
        return flashCard;
    }
}