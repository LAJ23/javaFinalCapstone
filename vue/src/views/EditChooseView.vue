<template>
    <Header/>
    <h2>Choose a deck to Edit:</h2>
    <UserDecks />
  <DeckIcon
      v-for="deck in limitedDecks"
      :key="deck.deckId"
      :name="deck.deckName"
      :highScore="deck.highScore"
      :color="deck.color"
      :linkTarget="{ name: 'editDeck', params: { deckId: deck.deckId } }"
  />






</template>
  
  <script>
  import Header from '../components/Header.vue';
  import UserDecks from '../components/UserDecks.vue';
  import DeckIcon from "@/components/DeckIcon.vue";

  
  
  export default {
    components: {
      DeckIcon,
      Header,
      UserDecks,
      
    }
  };
  </script>
  
  <style scoped>
  * {
    font-family: 'Writing';
    padding-left: 10vw;
    padding-right: 10vw;
  
  
  }
  
  h2 {
    font-size: 3vw;
    margin-top: 5vw;
  }
 
  </style>
