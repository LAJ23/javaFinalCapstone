<template>
  <div id="iconCont">
    <DeckIcon
      v-for="deck in decks"
      :key="deck.deckId"
      :name="deck.deckName"
      :highScore="deck.highScore"
      :color="deck.color"
    />
  </div>
</template>

<script>
import DeckIcon from './DeckIcon.vue'; // Adjust the path as necessary
import FlashcardService from '../services/FlashcardService';

export default {
  data() {
    return {
      decks: [],
    };
  },
  created() {
    this.fetchDecks();
  },
  methods: {
    async fetchDecks() {
      try {
        const response = await FlashcardService.getDecks(this.$store.state.user.id);
        this.decks = response.data; // Adjust according to how your data is returned
      } catch (error) {
        console.error('Error fetching decks:', error);
      }
    },
  },
  components: {
    DeckIcon,
  },
};
</script>
  
  <style scoped>
  @font-face {
    font-family: 'Writing';
    src: url(../assets/Fonts/LovelexieHandwritten.ttf);
  }
  * {
   
  }
  h2 {
    font-size: 3vw;
    font-weight: 200;
  }
  .deck-icon {
    display: flex;
    width: 100%;
    justify-content: space-evenly;
    
  }

  .redBK {
    background-color: rgb(255, 67, 67);
  }
  .greenBK {
    background-color: rgb(127, 255, 95);
  }
  .yellowBK {
    background-color: rgb(238, 226, 67);
  }

  .iconText {

  }
  
  .deck-image {

  }
  .deckIconCont {
  
  }
  h3 {
   
  }
  #recentDecks {
   
  }
  </style>