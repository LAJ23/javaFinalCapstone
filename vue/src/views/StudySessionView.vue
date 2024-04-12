<template>
    <Header/>
    <h2>Name of Deck</h2>
    <div id="sessionCont">
    <div id="topBar">
    <button class="btn">End Session</button>
    <button class="btn">Skip</button>
    <p>Time 00:00</p>
    <p>Score 2/10</p>
    <div class="btn btn2"><font-awesome-icon :icon="['fas', 'pencil']" /> </div>
    
</div>
<!--     
    <FlashFront /> -->
    <FlashBack />
    
    <div id="bottom">
      <div id="tags">
          <h4>Tags</h4>
          <ul>
          <li class="">Javascript</li>
          <li>Loops</li>
      </ul>
      </div>
      
</div>
    </div>

 
   

  
          
   
  </template>
  
  <script>
  import Header from '../components/Header.vue';
  import FlashFront from '../components/FlashFront.vue';
  import FlashBack from '../components/FlashBack.vue';

  

  
  
  export default {
    components: {
    Header,
    FlashFront,
    FlashBack,
}
  };
  </script>
  
  <style scoped>
  * {
    font-family: 'Writing';
  
  
  }

  #answer {
    display: flex;
    
  }
.answerbtn {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 5vw;
  border-radius: 50%;
  width: 5vw;
  background-color: green;
}
.red {
  background-color: red;
  margin-left: 2vw;
}
  .thumbs-down-icon {
    color: rgb(255, 255, 255);
    font-size: 2.5vw;
  }
  .thumbs-up-icon {
    color: rgb(255, 255, 255);
    
    font-size: 2.5vw;
  }

  #bottom {
 display: flex;
 width: 50%;
 justify-content: space-between;
 margin-top: 3vw;
 padding-left: 20vw;
 padding-right: 20vw;
  }
  h2 {
    margin-top: 5vw;
    font-size: 3vw;
    font-weight: 200;
    margin-left: 20vw;
    margin-bottom: 2vw;
  }
h4 {
    font-size: 2vw;
    font-weight: 500;
}

p {
    font-size: 1.5vw;
}
button {
    margin: none;
}
  .card {
    width: 60%;
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    background-color: rgb(94, 202, 67);
    height: 30vw;
    
  }

  .card p {
    font-family: Arial, Helvetica, sans-serif;
    font-size: 5vw;
  }
  #topBar {
    display: flex;
    margin-bottom: .5vw;
    width: 60%;
    align-items: center;
    justify-content: space-between;
  }
  
  #flip {
    position: absolute;
    font-size: 3vw;
    padding: 1vw;
    padding-left: 5vw;
    padding-right: 5vw;
    border-radius: 50%;
    bottom: -2.7vw;
    left: 37%;
    background-color: orange;
  }
  #sessionCont {
    display: flex;
    flex-direction: column;
    align-items: center;

  }

  #tags {
    align-self: self-start;
    
    margin-top: 2vw;
  }

  li {
    background-color: blue;
    margin-top: 1vw;
    color: white;
    width: 12vw;
    border-radius: 2vw;
    text-align: center;
    padding-top: .5vw;
    padding-bottom: .5vw;
    font-size: 1.5vw;
  }
  .btn {
    background-color: blue;
    
    color: white;
    width: 12vw;
    border-radius: 1vw;
    border: none;
    text-align: center;
    padding-top: .5vw;
    padding-bottom: .5vw;
    font-size: 1.5vw;
  }
  .btn2 {
 width: 3vw;
 
  }
  </style>
  