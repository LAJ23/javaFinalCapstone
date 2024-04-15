<template>

    <h2>Choose a deck to Edit:</h2>
    <UserDecks route-name="editDeck" />
  </template>
  <script>
  import UserDecks from '../components/UserDecks.vue';
  export default {
    components: {
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
