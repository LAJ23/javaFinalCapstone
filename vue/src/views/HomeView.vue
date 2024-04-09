<template>
  <Header />
  <div class="home">
    <h1>Welcome, {{ user }}</h1>
    <h2>What is your goal today?</h2>
    <nav>
      <router-link
        class="btn"
        :to="{ name: 'study' }"
        style="
          display: inline-flex;
          text-decoration: none;
          align-items: center;
          justify-content: center;
          height: 3vw;
          padding-top: 1vw;
        "
      >
        Study
      </router-link>
      <router-link
        v-on:click="goToCreate"
        class="btn"
        :to="{ name: 'study' }"
        style="
          display: inline-flex;
          text-decoration: none;
          align-items: center;
          justify-content: center;
          height: 3vw;
          padding-top: 1vw;
        "
      >
        Create Deck
      </router-link>
      <router-link
        class="btn"
        :to="{ name: 'study' }"
        style="
          display: inline-flex;
          text-decoration: none;
          align-items: center;
          justify-content: center;
          height: 3vw;
          padding-top: 1vw;
        "
      >
        Edit Deck
      </router-link>
    </nav>
    <h2>Recent Decks</h2>
    <UserDecks />
  </div>
</template>

<script>
import Header from '../components/Header.vue';
import UserDecks from '../components/UserDecks.vue';
import UserService from '../services/UserService'


export default {
  components: {
    Header,
    UserDecks,
  },
    data() {
      return {
        newDeck: {},
        newCard: {},
        nextDeckId: 2
      };
    },
    mounted() {
      this.fetchData();
    },
    methods: {
      fetchData() {
      fetch('/api/data').then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        }).then(data => {
          this.data = data;
        }).catch(error => {
          console.error('Error fetching data:', error);
        });
       },
<<<<<<< HEAD
       getNextDeckId(){
          return this.nextDeckId++;
       },
       createDeck(){
          this.newDeck.id =

=======
       goToCreate(){
        this.$router.push("/create");
>>>>>>> 38288b2 (create deck method)
       }

  },
}
</script>

<style scoped>
* {
  font-family: "Writing";
}

.home {
  margin: 7vw;
  display: flex;
  flex-direction: column;
}

h1 {
  font-size: 5vw;
  margin: 0;
  font-weight: 500;
}
h2 {
  font-size: 2.5vw;
  margin-top: 2vw;
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
  text-decoration: none; /* Remove underline from links */
  cursor: pointer; /* Ensure it looks clickable */
}

.recentDecks {
  display: flex;
  flex-direction: row; /* or column */
  justify-content: space-between; /* Alignment along the main axis */
  align-items: center; /* Alignment along the cross axis */
}

.deckIcon {
  width: 20vw;
}
</style>
