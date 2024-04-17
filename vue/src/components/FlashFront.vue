<template>

  <div :class="['card', colorClass]">
    <p>{{ card.question }}</p>
    <button id="flip" @click="flip">Flip!</button>
  </div>
</template>

<script>
export default {
  props: ['card', 'color'],
  mounted() {
    console.log('Component mounted with card:', this.card);
  },
  watch: {
    card(newVal, oldVal) {
      console.log('Card prop updated:', newVal);
    }
  },
  computed: {
    colorClass() {
      switch (this.color) {
        case 1: return 'redBK';
        case 2: return 'orangeBK';
        case 3: return 'yellowBK';
        case 4: return 'greenBK';
        case 5: return 'whiteBK';
        default: return '';
      }
    },
  },
  methods: {
    flip() {
      this.$emit('flipCard');
    }
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
    padding: 2vw;
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    
    height: 30vw;
    border-radius: 2vw;
    
  }

  .card p {
    font-family: Arial, Helvetica, sans-serif;
    font-size: 2.5vw;
  }

  
  #flip {
    position: absolute;
    font-size: 3vw;
    padding: 1vw;
    padding-left: 5vw;
    padding-right: 5vw;
    border-radius: 50%;
    bottom: -2.7vw;
    left: 38%;
    background-color: orange;
  }
  </style>
