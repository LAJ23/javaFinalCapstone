import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:3000"
});

export default {
    deck() {
        return http.get('/deck');
    },
    card() {
        return http.get('/card')
    },
    getDeck(id) {
        return http.get(`/deck/${id}`)
    },
    getCard(id) {
        return http.get(`/card/${id}`)
    },
    addDeck(deck) {
        return http.past('/deck', deck)
    },
    addCard(card) {
        return http.get('/card', card)
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