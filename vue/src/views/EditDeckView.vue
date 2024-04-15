<template>
  
    <div id="editView">
        <div id="editCont">
          <div id="top">
            <div>
              <h2>
      <template v-if="isEditingDeckName">
        <input class="deckTitle" v-model="deckName" @blur="toggleEditDeckName" @keyup.enter="toggleEditDeckName" placeholder="Enter Deck Name">
      </template>
      <template v-else>
        {{ deckName }}
        <div class="btn btn2" @click="toggleEditDeckName">
          <font-awesome-icon :icon="['fas', 'pencil']" />
        </div>
      </template>
    </h2>
    <h3>Color theme:
  <template v-if="isSelectingColor">
    <select id="colors" name="colors" @change="setColor">
      
<option  value="5">White</option>
<option class="redBK" value="1">Red</option>
<option class="orangeBK" value="2"><span>Orange</span></option>
<option class="yellowBK" value="3">Yellow</option>
<option class="greenBK" value="4">Green</option>

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
        <font-awesome-icon v-on:click="saveOrUpdateCard" class="savebtn" :icon="['fars', 'floppy-disk']" />
      </div>
        <div id="editorCont" >
        <div id="previewList">
          <button v-on:click="createBlankCard"><font-awesome-icon :icon="['fas', 'plus']" />   Add Card</button>
          <Preview />
          <Preview />
          <Preview />
        
    </div>
    <div id="cardEditor" >
      <div class="card" id="frontCard">
  <template v-if="isEditingFront">
    <input v-model="frontText" @blur="toggleEditFront" @keyup.enter="toggleEditFront" placeholder="Enter your question">
  </template>
  <template v-else>
    <p @click="toggleEditFront">{{ frontText }}</p>
  </template>
  <p class="side">Front</p>
</div>

<div class="card" id="backCard">
  <template v-if="isEditingBack">
    <input v-model="backText" @blur="toggleEditBack" @keyup.enter="toggleEditBack" placeholder="Enter your answer">
  </template>
  <template v-else>
    <p @click="toggleEditBack">{{ backText }}</p>
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
      // Store editable content
      frontText: 'Is water Wet?',
      backText: 'No, water is not wet',
      isEditingDeckName: false,
    deckName: 'Default Deck Name',
    isSelectingColor: false,
    selectedColor: 'Red', // Default or initial color
    newCard: {
      card_id: null,
      deck_id: null,
      question: '',
      answer: ''
    }
    };
  },
  components: {
   
    Preview,
  },
  created() {
  const deckId = this.$route.params.deckId;
  console.log("Received Deck ID:", deckId);  // Verify the deck ID is received
  if (deckId) {
    this.getDeck(deckId);
    this.fetchCards(deckId);
  } else {
    console.error('No deckId provided in the route.');
    this.$router.push({ name: 'home' });  // Fallback to a safe route if no ID
  }
},
  methods: {
    fetchCards(deckId) {
  FlashcardService.getCards(deckId).then(response => {
    this.cards = response.data;
    console.log('Cards fetched:', response.data);
  }).catch(error => {
    console.error('Failed to fetch cards:', error);
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
    this.isSelectingColor = false; // Optionally close the dropdown after selection
  },
  getNextCardId(){
    return this.nextCardId++;
  },
  createBlankCard(){
    FlashcardService.addBlankCard() ;{
      //create card in data()
    }
  },
  saveOrUpdateCard(card){
    for(let i = 0; i < cards.length; i++){
      if (card_id === null){
        FlashcardService.saveCard(this.newCard.deck_id, this.newCard.question, this.newCard.answer)
        .then(response => {

        })
        .catch(error => {
        console.error("Failed to create card:", error);
        });
      }else{
        FlashcardService.updateCard(this.newCard.deck_id, this.newCard.question, this.newCard.answer)
        .then(response => {

        })
        .catch(error => {
          console.error("Failed to update card:", error);
        })
      }
    }

  }
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

  #previewList {
    width: 25% ;
  
    row-gap: 1.5vw;
    padding-top: 1vw;
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: rgb(133, 133, 133);
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
    width: 75%;
    background-color: rgb(226, 226, 226);
    align-items: center;
    justify-items: center;

  }
  #editorCont {
    display: flex;
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
    
    background-color: rgb(255, 88, 88);;
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

  .savebtn {
    font-size: 3.5vw;
    position: absolute;
    right: 0;
    bottom: 1vw;
    
    

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
  </style>
