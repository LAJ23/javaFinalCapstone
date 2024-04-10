package com.techelevator.Service;

import com.techelevator.model.Flashcards;
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
    public Flashcards[] listFlashcards() {
        Flashcards[] getFlashd = null;
        try {
            ResponseEntity<Flashcards[]> response = restTemplate.exchange(API_BASE_URL + "", HttpMethod.GET,
                    makeAuthEntity(), Flashcards[].class);
            getFlashd = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Can't find Flashcards");
        }
        return getFlashd;
    }
    /**
     *Gets a singular card from its ID
     */
    public Flashcards getCard(int cardId) {
        Flashcards card = null;
        try {
            ResponseEntity<Flashcards> response =
                    restTemplate.exchange(API_BASE_URL + "" + cardId, HttpMethod.GET, makeAuthEntity(), Flashcards.class);
            card = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Can't find that specific card");
        }
        return card;
    }


    /**
     *Gets a list of all the decks
     */
    public Flashcards[] listDecks() {
        Flashcards[] getDeckd = null;
        try {
            ResponseEntity<Flashcards[]> response = restTemplate.exchange(API_BASE_URL + "", HttpMethod.GET,
                    makeAuthEntity(), Flashcards[].class);
            getDeckd = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Can't find Decks");
        }
        return getDeckd;
    }
    /**
     *Gets a singular deck from its ID
     */
    public Flashcards getDeck(int deckId) {
        Flashcards deck = null;
        try {
            ResponseEntity<Flashcards> response =
                    restTemplate.exchange(API_BASE_URL + "" + deckId, HttpMethod.GET, makeAuthEntity(), Flashcards.class);
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
