<template>
    <div :class="['endSession', colorClass]">
      <h2>Session Ended</h2>
      <h3 class="newScore" v-if="isNewHighScore"><font-awesome-icon :icon="['fas', 'star']" /> New High Score! <font-awesome-icon :icon="['fas', 'star']" /></h3>
      <h3>Score: {{ score }}%</h3>
      <h3>Time: {{ time }}</h3>
      <button @click="restartSession">Restart</button>
      <button @click="goHome">Back to Home</button>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      color: {
        type: Number,  
        required: true
      },
      score: {
        type: Number,
        default: 0
      },
      time: {
        type: String,
        default: ''
      },
      highScore: {
        type: Number,
        default: 0
      },
      oldScore: {
        type: Number,
        default: 0
      }
    },
    computed: {
      isNewHighScore() {
        return this.score > this.oldScore;
      },
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
      restartSession() {
        this.$emit('restart');
      },
      goHome() {
        this.$router.push({ name: 'home' });
      }
    }
  }
  </script>
  
  <style scoped>

  

    * {
      font-family: 'Writing';
    
    
    }
    .endSession {
    margin-top: 2vw;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-size: 2vw;
    
    padding: 5vw;
    line-height: 4vw;
    border-radius: 2vw;
    box-shadow: 0 0 2vw 0.5vw rgba(0, 0, 0, 0.2);

  }

  button {
    width: 100%;
    margin: .8vw;
    font-size: 1.5vw;
    color: white;
    background-color: rgb(63, 63, 255);
    padding: .5vw;
    border-radius: .5vw;
  }

  .newScore {
    background-color: yellow;
    padding: .5vw;
    padding-top: .8vw;
    border: 1px solid grey;
    border-radius: .5vw;
  }
    
  

    </style>