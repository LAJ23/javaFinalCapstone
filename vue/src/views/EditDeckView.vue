<template>

    <div id="editView">
        <div id="editCont">
          <div id="top">
            <div>
              <h2>
      <template v-if="isEditingDeckName">
        <input class="deckTitle" v-model="deck.deckName" @blur="toggleEditDeckName" @keyup.enter="toggleEditDeckName" placeholder="Enter Name">
      </template>
      <template v-else>
        {{ deck.deckName }}
        <div class="btn btn2" @click="toggleEditDeckName">
          <font-awesome-icon :icon="['fas', 'pencil']" />
        </div>
      </template>
    </h2>
    <h3>Color theme:
  <template v-if="isSelectingColor">
    <select id="colors" name="colors" @change="setColor">
      <option class="" value="">Select Color</option>
    <option class="redBK" value="1">Red</option>
    <option class="orangeBK" value="2">Orange</option>
    <option class="yellowBK" value="3">Yellow</option>
    <option class="greenBK" value="4">Green</option>
    <option class="whiteBK" value="5">White</option>
</select>

  </template>
  <template v-else>
    <span id="color">{{ selectedColor }}</span>
    <div id="themePen" @click="toggleColorSelection">
      <font-awesome-icon :icon="['fas', 'pencil']" />
    </div>
  </template>
</h3> 

       </div> 
       <div class="btns">
        <button class="btn deletebtn" @click="deleteDeck">Delete Deck</button>
          <span class="btn savebtn" @click="saveOrUpdateDeck">
            Save This Deck 
            <font-awesome-icon icon="far, fa-floppy-disk" />
          </span>
          
       </div>
       
      </div>
        <div id="editorCont" >
          <div class="previewBtnFix">
            <button class="addbtn" v-on:click="createBlankCard">
            <font-awesome-icon :icon="['fas', 'plus']" /> Add Card
          </button>
          <div id="previewCont">
            
        <div id="previewList">
          
          <Preview 
      v-for="(card, index) in cards"
      :key="card.card_id"
      :card_id="card.card_id"
      :deckId="deck.deckId"
      :question="card.question"
      :answer="card.answer"
      :color="deck.color"
      :index="index"
      :isSelected="index === selectedIndex"
      @selectCard="handleSelectCard(index, card)"
      @deleteCard="handleDeleteCard"
    />


  </div>
    </div>
  </div>
    <div id="cardEditor" >
      <div class="card" :class="colorClass" id="frontCard">
  <template v-if="isEditingFront">
    <input v-model="newCard.question" @blur="toggleEditFront" @keyup.enter="toggleEditFront" placeholder="Enter your question">
  </template>
  <template v-else>
    <p @click="toggleEditFront">{{ newCard.question }}</p>
  </template>
  <p class="side">Front</p>
</div>

<div class="card" :class="colorClass" id="backCard">
  <template v-if="isEditingBack">
    <input v-model="newCard.answer" @blur="toggleEditBack" @keyup.enter="toggleEditBack" placeholder="Enter your answer">
  </template>
  <template v-else>
    <p @click="toggleEditBack">{{ newCard.answer }}</p>
  </template>
  <p class="side">Back</p>
</div>
  </div>
</div>
</div>
</div>


    
 
  </template>
  
  <script>
  import Preview from '../components/FlashCardPreview.vue';
  import FlashcardService from '../services/FlashcardService';

  export default {
    data() {
      return {
        cards: [],
        deck: {
          deckName: '',
          highScore: 0,
          color: '',
          creator_id: null,
          deckId: ''
        },
        isEditingFront: false,
        isEditingBack: false,
        frontText: 'Is water Wet?',
        backText: 'No, water is not wet',
        isEditingDeckName: false,
        deckName: 'Default Deck Name',
        isSelectingColor: false,
        title: '',
        selectedIndex: 0,
        selectedColor: 'Red',
        newCard: {
          card_id: undefined,
          deck_id: null,
          question:'QUESTION',
          answer: 'ANSWER'
        }
      };
    },
    components: {

      Preview,
    },
    created() {
      const deckId = this.$route.params.deckId;
      console.log("Received Deck ID:", deckId);
      if (deckId) {
        this.getDeck(deckId);
        this.fetchCards(deckId);
      } else {
        console.error('No deckId provided in the route.');
        this.$router.push({name: 'home'});  // Fallback to a safe route if no ID
      }
    },
    methods: {
      deleteDeck() {
    if (!confirm('Are you sure you want to delete this deck?')) {
      return;
    }

    FlashcardService.deleteDeck(this.deck.deckId)
      .then(() => {
        alert('Deck has been deleted successfully.');
        this.$router.push({ name: 'edit' }); // Navigate back to the home or another relevant view
      })
      .catch(error => {
        console.error('Failed to delete deck:', error);
        alert('Error deleting deck. Please try again.');
      });
  },
      fetchCards(deckId) {
  FlashcardService.getCards(deckId).then(response => {
    this.cards = response.data;
    console.log('Cards fetched:', response.data);
    if (this.cards.length > 0) {

      this.frontText = this.cards[0].question;  // Set the frontText
      this.backText = this.cards[0].answer;     // Set the backText
    }
  }).catch(error => {
    console.error('Failed to fetch cards:' , error);
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
    console.log('Deck details fetched:', data);
  }).catch(error => {
    console.error('Failed to fetch deck details:', error);
  });
},
  toggleEditFront() {
    // Check if frontText is empty and set a default value
    if (this.frontText.trim() === '') {
      this.frontText = 'Enter your question';
    }
    this.isEditingFront = !this.isEditingFront;
  },
  toggleEditBack() {
    // Check if backText is empty and set a default value
    if (this.backText.trim() === '') {
      this.backText = 'Enter your answer';
    }
    this.isEditingBack = !this.isEditingBack;
  },
  toggleEditDeckName() {
    if (this.deckName.trim() === '') {
      this.deckName = 'Unnamed Deck'; // Default deck name if empty
    }
    this.isEditingDeckName = !this.isEditingDeckName;
  },
  toggleColorSelection() {
    this.isSelectingColor = !this.isSelectingColor;
  },
  setColor(event) {
    this.selectedColor = event.target.options[event.target.selectedIndex].text;
    this.deck.color = event.target.value;
    console.log("Selected color value:", this.deck.color); // Debugging: Check the actual value being set
    this.isSelectingColor = false;
},
  handleSelectCard(index, card) {
    this.selectedIndex = index;
    this.newCard = card;  // Pointing newCard to the selected card
},
  handleDeleteCard({ card_id, index }) {
    if (card_id) {
      this.removeCardFromLocal(index);
      FlashcardService.deleteCard(card_id).then(() => {

        console.log("Card deleted from server:", card_id);
      }).catch(error => {
        console.error("Failed to delete card:", error);
      });
    } else {
      console.log(card_id)
      this.removeCardFromLocal(index);
    }
  },
  removeCardFromLocal(index) {
    this.cards.splice(index, 1);
  },
  getNextCardId(){
    return this.nextCardId++;
  },
  createBlankCard() {
    const newCard = {
        cardId: undefined, 
        deckId: this.deck.deckId, 
        question: "Enter Question",
        answer: "Enter Answer",
        color: this.deck.color, 
    };
    this.cards.push(newCard);
    this.selectedIndex = this.cards.length - 1;  
    this.newCard = newCard;  
},
saveOrUpdateDeck() {
  this.cards.forEach(card => {
     
    if (card.card_id === undefined) {  
      card.deck_id = this.deck.deckId; 
      FlashcardService.saveCard(card)
      .then(response => {
        console.log("Card was saved", response.data);
        card.card_id = response.data.card_id;  
      })
      .catch(error => {
        console.error("Failed to create card:", error);
      });
    } else {
      console.log(card) 
      FlashcardService.updateCard(card)
      .then(response => {
        console.log("Card was updated", response);
      })
      .catch(error => {
        console.error("Failed to update card:", error);
      });
    }
  });
},

},computed: {
  colorClass() {
    console.log("Current color code:", this.deck.color); // Debugging line to check what value is being used
    switch (this.deck.color) {
      case '1': return 'redBK';
      case '2': return 'orangeBK';
      case '3': return 'yellowBK';
      case '4': return 'greenBK';
      case '5': return 'whiteBK';
      default: return 'whiteBK';
    }
  },
},
  }
  </script>
  
  <style scoped>
  * {
    font-family: 'Writing';
   
  }

  select {
    font-size: 2vw;
  }
  .fa-floppy-disk {
    margin-left: 2vw;
    margin-bottom: .5vw;
  }


  #editCont {
    width: 90%;

  }
  h2 {
    font-size: 2.5vw;
    font-weight: 200;
  }
  .btn2 {
    display: inline;
    font-size: 1.5vw;
  }

  #themePen {
    font-size: 1vw;
    display: inline;
  }
  .deletebtn {
    width: 20vw;
    font-size: 2.5vw
    
  }

  .btns {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 5vw;
    position: absolute;
    bottom: 0;
    right: 0;
  }
  .savebtn {
    width: 25vw;
    height: 2.6vw;
    margin-left: 2vw;
    white-space: nowrap;
    font-size: 2.5vw;
    display: flex;
    justify-content: center;
    align-items: center;
   
    bottom: 1vw;
    cursor: pointer;
  }
  .previewBtnFix {
    width: 25%;
    height: 60vw;
    background-color: rgb(133, 133, 133);
    border-radius: 1vw;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top: 1.8vw;

  }
  #previewCont{
    overflow: scroll;
    
    display: flex;
    flex-direction: column;
    position: relative;
    width: 100%;
    height: 60vw;
  }
  #previewCont::-webkit-scrollbar {
    display: none;
}
#previewList::-webkit-scrollbar {
    display: none;}

#previewList {
  width: 100%;
 
  
  padding-top: 1vw;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  
  
  border-radius: 1vw;
  padding-bottom: 1.5vw;
  
}

  #editView {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    
    
  }

  #cardEditor {
    
    display: flex;
    flex-direction: column;
    width: 100%;
    
    background-color: rgb(226, 226, 226);
    align-items: center;
    justify-items: center;

  }
  #editorCont {
    display:flex;
    flex-direction: row;
    width: 100%;
    
  }

  h2 {
    margin-top: 3vw;
    font-size: 4vw;
  }
  h3 {
    margin-top: 1vw;
    margin-bottom: 2vw;
    font-size: 2vw;
  }
  
  .card {
    position: relative;
    display: flex;
    font-size: 2vw;
    align-items: center;
    justify-content: center;
    padding: 1vw;
    padding-left: 3vw;
    padding-right: 3vw;
    width: 70%;
    margin: 2vw;
    height: 25vw;
    
   
  }
  input {
    width: 90%;
    font-size: inherit;
    color: inherit;
    font-family: Helvetica, sans-serif;
  }
  .card p {
  font-family:  Helvetica, sans-serif;
  }

  .side {
    position: absolute;
    background-color: rgb(94, 94, 94);
    bottom: 1vw;
    padding: .2vw;
    color: white;
    right: 1.5vw;
    border-radius: .3vw;
  }
  #top {
    display: flex;
    justify-content: space-between;
    position: relative;
    
    

  }


  button {
    width: 80%;
    font-size: 2vw;
    border-radius: .5vw;
    border: none;
  }

  #color {
    color: rgb(255, 88, 88);
  }
  .deckTitle {
    font-family:  'Writing';
  }
  .redBK {
    background-color: rgb(255, 101, 101);
}
.orangeBK {
    background-color: rgb(255, 200, 99);
}
.yellowBK {
    background-color: rgb(255, 255, 97);
}

.greenBK {
    background-color: rgb(101, 255, 101);
}

.whiteBK {
    background-color: white;
    border: 1px solid black;
}
.addbtn {
  margin-bottom: 1vw ;
  
  left: 1.8vw;
  top: 1.4vw;
}
  </style>
