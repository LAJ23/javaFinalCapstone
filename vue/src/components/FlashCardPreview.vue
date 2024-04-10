<template>
    <div id="FlashCardCont" >
    
        
      <p>this is a test</p>
      <p id="num"><span>3</span></p>
</div>
    
 
  </template>
  
  <script>
  

  export default {
   
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
  
 
  </style>
