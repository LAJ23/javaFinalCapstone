<template>
    <Header/>
    <div id="createCont">
      <form  action="">
    <h3>What would you like to Name your deck?</h3>
    <input type="text" placeholder="Name">

    <h3>Choose a color theme:</h3>
    <select id="colors" name="colors">
  <option  value="white">White</option>
  <option id="red" value="red">Red</option>
  <option id="orange" value="orange"><span>Orange</span></option>
  <option id="yellow" value="yellow">Yellow</option>
  <option id="green" value="green">Green</option>
</select>
<button v-on:click="createDeck" class="btn" type="submit"> Create</button>
</form>
</div>
    
 
  </template>
  
  <script>
  import Header from '../components/Header.vue';

  export default {
    components: {
      Header
      
    },
    data() {
      return {
        newDeck: {},
      }
    },
    methods: {
      getNextDeckId(){
          return this.nextDeckId++;
       },
      createDeck(){
          this.$store.commit('ADD_DECK', this.newDeck);
          this.$router.push('/edit');

       }
    }
  };
  </script>
  
  <style scoped>
  * {
    font-family: 'Writing';
   
  }
h3 {
  font-size: 3vw;
  font-weight: 300;
  margin-bottom: 2vw;
}
span {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 2vw;
}
  #red{
    background-color: rgb(255, 114, 114);
   
  }
  #orange{
    background-color: rgb(255, 196, 114);
  }
  
  #yellow{
    background-color: rgb(246, 255, 114);
  }
  
  #green{
    background-color: rgb(114, 255, 140);
  }

  #createCont {
    padding-top: 5vw;
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
   
  }

  form {
    width: 60%;
   
  }

  input {
    width: 99.2%;
    padding-top: .5vw;
    font-size: 2vw;
    margin-bottom: 2vw;
  }
  .btn {
    width: 100%;
    font-size: 2vw;
    background-color: rgb(78, 78, 255);
    color: white;
    padding-top: .5vw;
    padding-bottom: .3vw;
  }
  
  select {
    width: 100%;
    font-size: 2vw;
    margin-bottom: 2vw;
    padding-top: .5vw;
   
  }

  
 
  </style>
  