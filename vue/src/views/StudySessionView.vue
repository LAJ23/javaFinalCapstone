<template>
  <div id="sessionCont">
    <h2>{{ deck.deckName }} Deck</h2>
    <div id="cardcont" v-if="sessionActive">
      <div id="topBar">
        <button class="btn" @click="endSession">End Session</button>
        <button class="btn" v-if="showSkipButton" @click="skipCard">Skip <font-awesome-icon :icon="['fas', 'forward-fast']" /></button>
        <p>Time {{ elapsedTime }}</p>
        <p>Score {{ currentScore }}/{{ totalCards }}</p>
      </div>
    
      <div class="wtf" v-if="!isLoading">
    <FlashFront
      v-if="showFront"
      :card="cards[currentCardIndex]"
      :color="deck.color"
      @flipCard="flipCard"
    />
    <FlashBack
      v-else
      :card="cards[currentCardIndex]"
      :color="deck.color"
      @answerSelected="handleAnswer"
    />
  </div>
  <div v-else>
    Loading cards...
  </div>

      <p class="cardCount">Card {{ currentCardIndex + 1 }}/{{ totalCards }}</p>
    </div>
    <EndSession
      v-else
      :oldScore = "oldScore"
      :color="deck.color"
      :score="scorePercentage"
      :time="elapsedTime"
      :highScore="deck.highScore"
      @restart="restartSession"
      @goHome="goHome"
    />
  </div>
</template>

<script>
import FlashcardService from '../services/FlashcardService'; 
import FlashFront from '../components/FlashFront.vue';
import FlashBack from '../components/FlashBack.vue';
import EndSession from '../components/EndSession.vue';

export default {
  components: {
    FlashFront,
    FlashBack,
    EndSession
  },
  data() {
    return {
      cards: [],
      currentCardIndex: 0,
      currentScore: 0,
      oldScore: 0,
      deck: {
        deckName: '',
        highScore: 0,
        color: '',
        creator_id: null,
        deckId: ''
      },
      totalCards: 0,
      showFront: true,
      startTime: null,
      timer: null,
      elapsedTime: "00:00",
      sessionActive: true,
      scorePercentage: 0 // Track the score percentage
    };
  },
  created() {
    const deckId = this.$route.params.deckId;
    if (deckId) {
      this.getDeck(deckId);
      this.fetchCards(deckId);
      this.startTimer();
    
    } else {
      console.error('No deckId provided');
      this.$router.push({ name: 'defaultRoute' });
    }
  },
  methods: {
    fetchCards(deckId) {
  FlashcardService.getCards(deckId).then(response => {
    this.cards = response.data;
    this.totalCards = this.cards.length;
    console.log("Current Card Index:", this.currentCardIndex);  // Check the index here
    console.log("Total cards:", this.totalCards);
  }).catch(error => {
    console.error('Failed to fetch cards:', error);
    this.cards = [];
  });
},
    getDeck(deckId) {
      FlashcardService.getDeck(deckId).then(response => {
        const data = response.data;
    this.deck.deckName = data.deckName;
    this.deck.highScore = data.highScore;
    this.deck.color = data.color;
    this.deck.creator_id = data.userID;
    this.deck.deckId = data.deckId;
    console.log(this.deck);

        this.oldScore = this.deck.highScore;
      }).catch(error => {
        console.error('Failed to fetch deck details:', error);
      });
    },
  
    startTimer() {
      this.startTime = new Date();
      this.timer = setInterval(() => {
        const now = new Date();
        const difference = new Date(now - this.startTime);
        const minutes = difference.getMinutes().toString().padStart(2, '0');
        const seconds = difference.getSeconds().toString().padStart(2, '0');
        this.elapsedTime = `${minutes}:${seconds}`;
      }, 1000);
    },
    stopTimer() {
      clearInterval(this.timer);
      this.timer = null;
    },
    endSession() {
      this.stopTimer();
      this.sessionActive = false;
      this.scorePercentage = Math.round((this.currentScore / this.totalCards) * 100);

      if (this.scorePercentage > this.deck.highScore) {
        this.deck.highScore = this.scorePercentage; // Update the deck's high score if new high score is achieved
        FlashcardService.updateDeck(this.deck).then(response => {
          console.log('High score updated successfully', response);
        }).catch(error => {
          console.error('Failed to update high score:', error);
        });
      }
    },
    restartSession() {
      this.currentCardIndex = 0;
      this.currentScore = 0;
      this.showFront = true;
      this.sessionActive = true;
      this.startTimer();
      this.fetchCards(this.deck.deckId);
    },
    goHome() {
      this.$router.push({ name: 'home' });
    },
    flipCard() {
      this.showFront = !this.showFront;
    },
    handleAnswer(correct) {
      if (correct) {
        this.currentScore++;
      }
      this.currentCardIndex++;
      if (this.currentCardIndex < this.totalCards) {
        this.showFront = true;
      } else {
        this.endSession();
      }
    },
    skipCard() {
      const skippedCard = this.cards.splice(this.currentCardIndex, 1)[0];
      this.cards.push(skippedCard);
      if (this.currentCardIndex < this.cards.length - 1) {
        this.showFront = true;
      } else {
        this.currentCardIndex = 0;
        this.showFront = true;
      }
    }
  },
  computed: {
    showSkipButton() {
      return this.currentCardIndex < this.cards.length - 1;
    }
  }
};
</script>


<style scoped>
 * {
    font-family: 'Writing';
  
  
  }
  .wtf {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .cardCount {
    position: absolute;
    bottom: -2.5vw;
    right: 19vw;

  }
  #cardcont {
    width: 100%;
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  h2 {
    font-size: 5vw;
    margin-top: 3vw;
    font-weight: 200;
    
  }
#sessionCont {
  display: flex;
  width: 100%;
  
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

#topBar {
  display: flex;
  margin-top: 2vw;
  justify-content: space-between;
  width: 65%;
  font-size: 2vw;
}
#topBar p {
  align-self: center;
}

.btn {
  
  font-size: 2vw;
  padding-top: .5vw;
  height: 4vw;
  margin: 5px;
  background-color: #007BFF;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>