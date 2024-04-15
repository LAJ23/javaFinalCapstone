<template>
  <div id="viewCont">
    <div id="iconCont">
      <DeckIcon
        v-for="deck in decks"
        :key="deck.deckId"
        :name="deck.deckName"
        :highScore="deck.highScore"
        :color="deck.color"
        :deckId="deck.deckId"
        :routeName="routeName"  
      />
    </div>
  </div>
</template>

<script>
import DeckIcon from './DeckIcon.vue';
import FlashcardService from '../services/FlashcardService';

export default {
  props: ['routeName'],  // Receive routeName prop
  components: {
    DeckIcon,
  },
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
        this.decks = response.data; // Ensure the API response format matches your expectations
      } catch (error) {
        console.error('Error fetching decks:', error);
      }
    },
  }
};
</script>
  
  <style scoped>
  @font-face {
    font-family: 'Writing';
    src: url(../assets/Fonts/LovelexieHandwritten.ttf);
  }

  h2 {
    font-size: 3vw;
    font-weight: 200;
  }
  #iconCont {
    margin-top: 2vw;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  column-gap: 6vw;
  
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

  #viewCont {
    display: flex;
    justify-content: center;
    align-items: center;
    padding-left: 15vw;
   
    
  }

  </style>