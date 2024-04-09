<template>
    <Header/>
    <h2>Choose a deck start a session:</h2>
    <UserDecks />
   

  
          
   
  </template>
  
  <script>
  import Header from '../components/Header.vue';
  import UserDecks from '../components/UserDecks.vue';

  
  
  export default {
    components: {
      Header,
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
  