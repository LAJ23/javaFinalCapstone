<template>
  <div :class="['card', colorClass]">
    <p>{{ card.answer }}</p>
    <div id="answer">
      <div class="answerbtn" @click="selectAnswer(true)">
        <font-awesome-icon class="thumbs-up-icon" :icon="['fas', 'thumbs-up']" />
      </div>
      <div class="answerbtn red" @click="selectAnswer(false)">
        <font-awesome-icon class="thumbs-down-icon" :icon="['fas', 'thumbs-down']" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['card', 'color'],
  computed: {
    colorClass() {
      switch (this.color) {
        case 1: return 'redBK';
        case 2: return 'orangeBK';
        case 3: return 'yellowBK';
        case 4: return 'greenBK';
        case 5: return 'whiteBK';
        default: return '';  // Default case if color is not set
      }
    },
  },
  methods: {
    selectAnswer(correct) {
      this.$emit('answerSelected', correct);
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
    position: absolute;
    bottom: -11vw;
  }
.answerbtn {
  display: flex;
  
  align-items: center;
  justify-content: center;
  height: 5vw;
  border-radius: 50%;
  width: 5vw;
  border: .4vw solid white;
  background-color: green;
  margin: 8vw;
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
    font-size: 2vw;
  }
  #flip
  {
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
  </style>
