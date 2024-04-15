<template>

  <div class="home">
    <h1>Welcome {{ userName }},</h1>
    <h2>What is your goal today?</h2>
    <nav>
      <router-link
        class="btn"
        :to="{ name: 'study' }"
        style="display: inline-flex; text-decoration: none; align-items: center; justify-content: center; height: 3vw; padding-top: 1vw;"
      >
        Study
      </router-link>
      <router-link
        v-on:click="goToCreate"
        class="btn"
        :to="{ name: 'create' }" 
        style="display: inline-flex; text-decoration: none; align-items: center; justify-content: center; height: 3vw; padding-top: 1vw;"
      >
        Create Deck
      </router-link>
      <router-link
        class="btn"
        :to="{ name: 'edit' }"
        style="display: inline-flex; text-decoration: none; align-items: center; justify-content: center; height: 3vw; padding-top: 1vw;"
      >
        Edit Deck
      </router-link>
    </nav>
    <h2>Recent Decks</h2>
    <div id="UserDeckCont" >
      <UserDecks route-name='session' />
  </div>
  </div>
</template>

<script>

import UserDecks from "../components/UserDecks.vue";
import UserService from "../services/UserService";

export default {
  components: {

    UserDecks,
  },
  data() {
    return {
      newDeck: {},
      newCard: {},
      nextDeckId: 2,
    };
  },
  computed: {
    userName() {
      return this.$store.state.user.username;
    },
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      fetch("/api/data")
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.json();
        })
        .then((data) => {
          this.data = data;
        })
          .then((data) => {
            this.decks = data.slice(0, 3); // Keep only the first 3 decks
          })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },

    getNextDeckId() {
      return this.nextDeckId++;
    },
    createDeck() {
      this.newDeck.id = this.getNextDeckId;
    },

    goToCreate() {
      this.$router.push("/create");
    },
  },
};
</script>

<style scoped>
* {
  font-family: "Writing";
}

.home {
  
  display: flex;
  flex-direction: column;
}

#UserDeckCont {
  margin-left: -14vw;
}

h1 {
  margin-top: 5vw;
  margin-left: 6vw;
  font-size: 5vw;
  
  font-weight: 500;
}
h2 {
  font-size: 2.5vw;
  margin-top: 3vw;
  margin-bottom: 2vw;
  align-self: center;
}

nav {
  display: flex;
  justify-content: space-evenly;
  margin-top: 2.5vw;
}

.btn {
  font-size: 2.5vw;
  width: 25%;
  padding-top: 1.2vw;
  padding-bottom: 1vw;
  color: white;
  background-color: rgb(58, 58, 255);
  border-radius: 0.8vw;
  border: none;
  text-decoration: none;
  cursor: pointer;
}

</style>
