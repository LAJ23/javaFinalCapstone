package com.techelevator.Service;

import com.techelevator.model.FlashCard;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class FlashcardService {
    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();

    private String authToken = null;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     *Gets a list of all the flash cards
     */
    public FlashCard[] listFlashcards() {
        FlashCard[] getFlashd = null;
        try {
            ResponseEntity<FlashCard[]> response = restTemplate.exchange(API_BASE_URL + "", HttpMethod.GET,
                    makeAuthEntity(), FlashCard[].class);
            getFlashd = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Can't find Flashcards");
        }
        return getFlashd;
    }
    /**
     *Gets a singular card from its ID
     */
    public FlashCard getCard(int cardId) {
        FlashCard card = null;
        try {
            ResponseEntity<FlashCard> response =
                    restTemplate.exchange(API_BASE_URL + "" + cardId, HttpMethod.GET, makeAuthEntity(), FlashCard.class);
            card = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Can't find that specific card");
        }
        return card;
    }


    /**
     *Gets a list of all the decks
     */
    public FlashCard[] listDecks() {
        FlashCard[] getDeckd = null;
        try {
            ResponseEntity<FlashCard[]> response = restTemplate.exchange(API_BASE_URL + "", HttpMethod.GET,
                    makeAuthEntity(), FlashCard[].class);
            getDeckd = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Can't find Decks");
        }
        return getDeckd;
    }
    /**
     *Gets a singular deck from its ID
     */
    public FlashCard getDeck(int deckId) {
        FlashCard deck = null;
        try {
            ResponseEntity<FlashCard> response =
                    restTemplate.exchange(API_BASE_URL + "" + deckId, HttpMethod.GET, makeAuthEntity(), FlashCard.class);
            deck = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Can't find that deck");
        }
        return deck;
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}
