import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
});

export default {
    getDeckName(id) {
        return http.get(`/deckName/${id}`)
    },
    getDeck(id) {
        return http.get(`/deck/${id}`)
    },

    addDeck(name, color) {
        let currentUser = JSON.parse(localStorage.getItem('user'))
        return http.post('/adddeck', {
            deckName: name,
            color: color,
            userID: currentUser.id});
    },

    updateDeck(deck) {
        return http.post('/updateDeck', {
          deckId: deck.deckId,
          deckName: deck.deckName,  // Changed from name to deckName
          highScore: deck.highScore,
          color: deck.color,
          user_id: deck.creator_id
        });
    },


    getDeck(id) {
        return http.get(`/decks/${id}`);
      },

    deck() {
        return http.get('/deck');
    },

    card() {
        return http.get('/card')
    },
    getDecks(id) {
        return http.get(`/decks/${id}`)
    },

    getCards(id) {
        return http.get(`/cards/${id}`)
    },

  
    addCard(card) {
        return http.post('/card', card)
    },

    updateCard(id) {
        return http.get(`/deck/${id}`)
    },
    deleteDeck(id) {
        return http.delete(`/deck/${id}`)
    },
    deleteCard(id) {
        return http.get(`/card/${id}`)
    },
}