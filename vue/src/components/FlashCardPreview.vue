<template>
    <div id="FlashCardCont" >
    
        
      <p>{{ question }}</p>
      <div class="deleteCont">
      <button><font-awesome-icon class="delete" :icon="['fas', 'x']" v-on:click="deleteCard"/></button>
      </div>
      <p id="num"><span>69</span></p>
    </div>
    
 
  </template>
  
  <script>
  import FlashcardService from '../services/FlashcardService';


  export default {
    props: ['deckId', 'question', 'color', 'answer', 'cardId'],
   methods: {
    deleteCard() {
      confirm('Are you sure you want to delete this card?');
    }
   },
  };
  </script>
  
  <style scoped>
  * {
    font-family: 'helvetica';
   
  }
  #FlashCardCont {
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 80%;
    height: 10vw;
    padding: 15px;
    text-align: center;
    vertical-align: middle;
    background-color: blueviolet;
  }

  #num {
    position: absolute;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 50%;
    bottom: .8vw;
    right: 1vw;
    height: 2vw;
    width: 2vw;
    color: white;
    background-color: rgb(0, 0, 0);
  }

  span {
    font-size: 1.5vw;
  }
  .delete {
  
    
    font-size: 1.5vw;
    color: black;

  }

  button {
    height: 2vw;
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid black;
    width: 2vw;
    position: absolute;
    top: 1vw;
    background-color: rgb(255, 68, 68);
    border-radius: 50%;
    right: 1vw;
  }
  
 
  </style>
