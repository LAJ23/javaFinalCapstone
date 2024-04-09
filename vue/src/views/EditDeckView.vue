<template>
    <Header/>
    <div id="editView">
        <div id="editCont">
        <h2>Deck Name <div class="btn btn2"><font-awesome-icon :icon="['fas', 'pencil']" /> </div></h2>
        <h3>color theme: Red <div id="themePen"><font-awesome-icon :icon="['fas', 'pencil']" /></div></h3>
        <div id="editorCont" >
        <div id="previewList">
       <Preview />
        <Preview />
        <Preview />
    </div>
    <div id="cardEditor" >
        <div class="card" id="frontCard" >

        </div>
        <div class="card" id="backCard" >

</div>
    </div>
</div>
      
</div>
</div>
    
 
  </template>
  
  <script>
  import Header from '../components/Header.vue';
  import Preview from '../components/FlashCardPreview.vue';

  export default {
    components: {
      Header,
      Preview,
      
    }
  };
  </script>
  
  <style scoped>
  * {
    font-family: 'Writing';
   
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
    width: 25%;
    row-gap: 1.5vw;
    padding-top: 1vw;
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: brown;
    padding-bottom: 1.5vw;
  }

  #editView {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    
    
  }

  #cardEditor {
    background-color: rgb(136, 150, 150);
    display: flex;
    flex-direction: column;
    flex-grow: 1;
    width: auto;
    align-items: center;
    justify-items: center;

  }
  #editorCont {
    display: flex;
    width: 100%;
    
  }

  h2 {
    margin-top: 2vw;
  }
  h3 {
    margin-top: 1vw;
    margin-bottom: 2vw;
  }
  
  .card {
    
    width: 70%;
    margin: 2vw;
    height: 25vw;
    background-color: burlywood;
  }
  </style>
  