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
      <UserDecks routeName='session' :limit-top-three="true" />
  </div>
  </div>
</template>

<script>

import UserDecks from "../components/UserDecks.vue";

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
      const name = this.$store.state.user.username;
      if (name && name.length > 0) {
        return name.charAt(0).toUpperCase() + name.slice(1);
      }
      return '';
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
  margin-top: 3vw;
  margin-left: 3vw;
  font-size: 4vw;
  
  font-weight: 500;
}
h2 {
  font-size: 2.5vw;
  margin-top: 3vw;
  margin-bottom: 1vw;
  align-self: center;
}

nav {
  display: flex;
  justify-content: space-evenly;
  margin-top: 1.5vw;
}
</style>
