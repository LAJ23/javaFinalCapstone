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
        {
          card_id: 1,
          deck_id: 1,
          question: "What is a variable? Give an example",
          answer:
            "Variables are containers for storing data values. An example is a boolean variable named isAwake, whose value can be either true or false.",
        },
        {
          card_id: 2,
          deck_id: 1,
          question:
            "What programming problem does a loop solve? Why would we use a loop?",
          answer:
            "A loop solves the problem of repeating tasks in code. We use a loop to perform an operation multiple times without writing the same code repeatedly. This makes our code more efficient, easier to read, and simpler to maintain.",
        },
        {
          card_id: 3,
          deck_id: 1,
          question: "What is an object? What is a class?",
          answer:
            "An object is an instance of a class. Its a data structure that contains data and methods. A class is a blueprint for creating objects. It defines the data and behavior that the created objects can have.",
        },
        {
          card_id: 4,
          deck_id: 1,
          question: "What is Encapsulation?",
          answer:
            "Encapsulation is a principle of object-oriented programming where the data (variables) and methods operating on that data are bundled together in a class. Its important because it hides the internal state of one object from others. This enhances security, prevents data corruption, and makes code easier to maintain and understand.",
        },
        {
          card_id: 5,
          deck_id: 1,
          question: "What is Inheritance?",
          answer:
            "Inheritance is a concept in object-oriented programming where a class can inherit properties and methods from another class. You would use inheritance to promote code reusability and create a logical, hierarchical structure for your classes. It helps in making code easier to manage, understand, and extend.",
        },
        {
          card_id: 6,
          deck_id: 1,
          question: "What is Polymorphism?",
          answer:
            "Polymorphism, a concept in object-oriented programming, allows one interface to represent different underlying forms. This could mean methods with the same name behaving differently based on their data types or number of parameters. Polymorphism is similar to Inheritance in that they both promote flexibility and extensibility in code. However, while Inheritance allows classes to inherit properties and methods from a parent class, Polymorphism allows methods to perform different actions based on the object they are acting upon.",
        },
        {
          card_id: 7,
          deck_id: 1,
          question: "What is an Interface, and why would you use one?",
          answer:
            "An Interface defines a contract for classes. It outlines specific methods or properties a class must implement. You use interfaces to enforce certain behaviors across different classes. This enables loose coupling, making your code more modular, adaptable, and testable.",
        },
        {
          card_id: 8,
          deck_id: 2,
          question: "How many states are in the United States?",
          answer: "50",
        },
        {
          card_id: 9,
          deck_id: 2,
          question: "What is the biggest State in the United States?",
          answer: "ALASKA",
        },
        {
          card_id: 10,
          deck_id: 2,
          question: "What is the smallest State in the United States?",
          answer: "Rhode Island",
        },
        {
          card_id: 11,
          deck_id: 2,
          question: "What is the Capital of the United States?",
          answer: "Washington D.C.",
        },
        {
          card_id: 12,
          deck_id: 2,
          question:
            "How many permanently inhabited territories (not states) are part of the United States?",
          answer: "5",
        },
        {
          card_id: 13,
          deck_id: 2,
          question:
            "How do we refer to the first ten amendments of the constitution?",
          answer: "The Bill of Rights",
        },
        {
          card_id: 14,
          deck_id: 3,
          question: "What are the three branches of Government?",
          answer: "Executive, Legislative, Judicial",
        },
        {
          card_id: 15,
          deck_id: 3,
          question: "What is the smallest State in the United States?",
          answer: "Rhode Island",
        },
        {
          card_id: 16,
          deck_id: 3,
          question: "Who was the second president of the United States?",
          answer: "John Adams",
        },
        {
          card_id: 17,
          deck_id: 3,
          question: "When was the USA founded?",
          answer: "July 4, 1776",
        },
        {
          card_id: 18,
          deck_id: 4,
          question: "Who wrote the book '1984'?",
          answer: "George Orwell",
        },
        {
          card_id: 19,
          deck_id: 4,
          question:
            "What book opens with the line 'It was the best of times, it was the worst of times, it was the age of wisdom, it was the age of foolishness...",
          answer: "A Tale of Two Cities",
        },
        {
          card_id: 20,
          deck_id: 4,
          question: "How many novels has author Steven King published?",
          answer: "65 Novels",
        },
        {
          card_id: 21,
          deck_id: 4,
          question: "Who is the main character in The Odyssey?",
          answer: "Odysseus",
        },
        {
          card_id: 22,
          deck_id: 4,
          question: "Who wrote The Odyssey?",
          answer: "Homer",
        },
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
        state.decks.push(deck);
      },
    },
  });
  return store;
}
