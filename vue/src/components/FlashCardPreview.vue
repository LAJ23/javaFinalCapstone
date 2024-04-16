<template>
  <div id='Flashhighlight' :class="isSelected ? 'FlashhighlightSelected' : 'Flashhighlight'" @click="selectCard">
    <div id="FlashCardCont">
      <p>{{ question }}</p>
      <div class="deleteCont">
        <button @click.stop="deleteCard">
          <font-awesome-icon class="delete" :icon="['fas', 'x']" />
        </button>
      </div>
      <p id="num"><span>{{ index + 1 }}</span></p>
    </div>
  </div>
</template>

<script>
import FlashcardService from '../services/FlashcardService';

export default {
  props: ['isSelected', 'deckId', 'question', 'answer', 'cardId', 'index', 'color'],
  methods: {
    selectCard() {
      // Emit an event to inform the parent component that this card was selected
      this.$emit('selectCard');
    },
    deleteCard() {
      if (confirm('Are you sure you want to delete this card?')) {
        console.log(this.cardId, this.index)
        this.$emit('deleteCard', { cardId: this.cardId, index: this.index });
      }
    }
  },
};
</script>
  
  <style scoped>
  * {
    font-family: 'helvetica';

  }
  p {font-size: .8vw;}
   #FlashCardCont {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 85%;
  border-radius: .5vw;
  height: 75%; 
  padding: .5vw;
  text-align: center;
  vertical-align: middle;
  background-color: blueviolet;
} 
#Flashhighlight {
  
  border-radius: .5vw;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  
  height: 13vw ;
  
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
  .FlashhighlightSelected {
  background-color: green;  /* Ensure this class overrides the default */
}
  
 
  </style>
