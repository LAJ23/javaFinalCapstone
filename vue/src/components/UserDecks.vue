<template>
    <div class="deck-icon">
           <DeckIcon />
           <DeckIcon />
           <DeckIcon />
      
    </div>

  </template>
  
  <script>
  
  import DeckIcon from './DeckIcon.vue';
  
  export default {
    data() {
      return {
      
      };
    },
    components:{
        DeckIcon,
    }
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