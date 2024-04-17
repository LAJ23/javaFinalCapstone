<template>
  <div id="viewCont">
    <div id="iconCont">
      <DeckIcon
        v-for="deck in decks"
        :key="deck.deckId"
        :deckId="deck.deckId"
        :color="deck.color"
        :name="deck.deckName"
        :highScore="deck.highScore"
        :routeName="routeName"
      />
    </div>
  </div>
</template>

<script>
import DeckIcon from './DeckIcon.vue';
import FlashcardService from '../services/FlashcardService';

export default {
  props: {
    routeName: String,
    limitTopThree: {
      type: Boolean,
      default: false
    }
  },
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
        let decks = response.data || [];
        // Apply the sorting and limiting only if limitTopThree is true
        if (this.limitTopThree && decks.length) {
          decks = decks.sort((a, b) => b.deckId - a.deckId);
          decks = decks.slice(0, 3);
        }
        this.decks = decks;
      } catch (error) {
        console.error('Error fetching decks:', error);
        this.decks = [];
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
    margin-top: 1vw;
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
