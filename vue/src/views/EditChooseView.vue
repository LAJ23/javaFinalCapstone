<template>
  
<div id="top">
    <h2>Choose a deck to Edit:</h2>
  </div>
    <UserDecks routeName="editDeck" />
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
    padding-left: 5vw;
  }
  #top {
    display: flex;
    align-items: flex-end;
    justify-content: space-between;
  }

  button {
    margin-right: 3vw;
    display: flex;             
    align-items: center;       
    justify-content: center;
    font-size: 2.5vw;
    width: 25%;
    height: 4vw;
    white-space: nowrap;
    color: white;
    background-color: rgb(58, 58, 255);
    border-radius: 0.8vw;
    border: none;
    text-decoration: none;
    cursor: pointer;
  }
  </style>
