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
    getDecks(id) {
        return http.get(`/decks/${id}`)
    },
    getCard(id) {
        return http.get(`/card/${id}`)
    },
    addDeck(name, color, creator_id) {
        console.log(name, color, creator_id);
        return http.post('/adddeck', {
            name: name, 
            color: color, 
            creator_id: creator_id});
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