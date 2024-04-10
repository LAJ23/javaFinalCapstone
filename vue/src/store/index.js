import { createStore as _createStore } from "vuex";
import axios from "axios";

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || "",
      user: currentUser || {},
      nextDeckId: 5,
      decks: [
        {
          id: 1,
          name: "Java",
          high_score: 0,
          color: 1,
          creator_id: 2,
        },
        {
          id: 2,
          name: "elementary",
          high_score: 0,
          color: 2,
          creator_id: 2,
        },
        {
          id: 3,
          name: "highschool",
          high_score: 0,
          color: 3,
          creator_id: 2,
        },
        {
          id: 4,
          name: "authors",
          high_score: 0,
          color: 4,
          creator_id: 2,
        },
      ],
      flashcards: [
        // {
        //   card_id:
        //   deck_id: 1,
        //   question:
        //   answer:
        // }
      ],
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem("token", token);
        axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem("user", JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        state.token = "";
        state.user = {};
        axios.defaults.headers.common = {};
      },
      ADD_DECK(state, deck) {
        deck.id = state.nextDeckId++;
        state.decks(deck);
      },
    },
  });
  return store;
}
