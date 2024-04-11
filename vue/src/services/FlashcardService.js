import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
});

export default {
    getDeck(id) {
        return http.get(`/decks/${id}`); 
      },



    deck() {
        return http.get('/deck');
    },
    card() {
        return http.get('/card')
    },
    getCard(id) {
        return http.get(`/card/${id}`)
    },
    addDeck(deck) {
        return http.post('/deck', deck)
    },
  
    addCard(card) {
        return http.post('/card', card)
    },
    updateDeck(deck) {
        return http.put(`deck/${deck.id}`)
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