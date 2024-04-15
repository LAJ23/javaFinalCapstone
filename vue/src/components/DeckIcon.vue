<template>
  <div id="iconCont">
    <router-link :to="{ name: routeName, params: { deckId: deckId, name: name }}" style="text-decoration: none; padding: 0;">
      <div class="deckIconCont">
        <div :class="['iconText', colorClass]">
          <h3>{{ name }}</h3>
          <p>High Score: {{ highScore }}%</p>
        </div>
        <img :src="deckImage" alt="" class="deck-image">
      </div>
    </router-link>
  </div>
</template>

<script>
import deckImage from '../assets/imgs/deck-image.png';

export default {
  props: ['name', 'highScore', 'color', 'deckId', 'routeName'],  // Add 'routeName' prop
  data() {
    return {
      deckImage,
    };
  },
  computed: {
    colorClass() {
      switch (this.color) {
        case 1: return 'redBK';
        case 2: return 'orangeBK';
        case 3: return 'yellowBK';
        case 4: return 'greenBK';
        case 5: return 'whiteBK';
        default: return 'whiteBK';
      }
    },
  },
};
</script>
  
  <style scoped>
  @font-face {
    font-family: 'Writing';
    src: url(../assets/Fonts/LovelexieHandwritten.ttf);
  }
  * {
    width: 100%;
  }
#iconCont {
  width: 20vw;
}



.deck-image {
  width: 20vw;
}
.deckIconCont {
    position: relative;
    
    
}



.iconText {
    position: absolute;
    display: flex;
    flex-direction: column;
    text-align: center;
    justify-content: center;
    height: 12vw;
    width: 16.5vw;
    border-radius: .5vw;
    bottom: 1.3vw;
    right: 1vw;
   
    color: black;   
}

h3 {
    font-size: 3vw;
    margin-bottom: 2vw;
}

p {
    font-size: 1.8vw;
}
</style>
