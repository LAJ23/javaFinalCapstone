<template>
  <header>
    <router-link
      class="logo-link"
      style="height: 3vw; padding: 0"
      :to="{ name: 'home' }"
    >
      <img
        src="../assets/imgs/Flashr-Logo-white.png"
        alt="App logo"
        id="appLogo"
      />
    </router-link>
    <div id="searchLogoutCont">
      <router-link
        style="
          text-decoration: none;
          color: inherit;
          font-family: 'Writing';
          font-size: 2vw;
          color: white;
        "
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link>
      <div id="searchBar">
      <div class="input-icon">
      <i class="fa-solid fa-magnifying-glass"></i>
      <input type="text" v-model="searchQuery" placeholder="Search" @input="searchCards" @keydown.down="selectNext" @keydown.up="selectPrevious" />
      </div>
      <ul v-if="filteredCards.length > 0" class="search-results">
        <li v-for="card in filteredCards" @click="selectItem(card)" :key="card.card_id">
            {{ card.question }}
            {{ card.answer }}
        </li>
    </ul>
    </div>
  </div>
  </header>

  
</template>

<script>
import FlashcardService from '../services/FlashcardService' 

export default {
  data() {
    return {
    searchQuery: '',
    filteredCards: []
    };
  },
  methods: {
    goHome() {
      this.$router.push("/");
    },
    searchCards() {
  const query = this.searchQuery.toLowerCase();
  FlashcardService.searchFlashcards(query)
    .then(response => {
      this.filteredCards = response.data;
    })
    .catch(error => {
      console.error('Error searching flashcards:', error);
    });
},
    selectItem(item) {
        this.searchQuery = item.question;
        this.filteredCards = [];
        console.log( "routercheck", item.deck_id)
        this.$router.push(`/edit-deck/${item.deck_id}`)
    },
  },
};
</script>

<style scoped>
@font-face {
  font-family: "Writing";
  src: url(../assets/Fonts/LovelexieHandwritten.ttf);
}

header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 5vw;
  background-color: rgb(52, 61, 190);
  padding: 0.3vw;
  padding-left: 3vw;
  padding-right: 5vw;
}

#appLogo {
  height: 100%;
}
#searchBar {
  position: relative;
  margin-left: 2vw;
}
#searchBar input {
  font-family: "Writing";
  font-size: 1.3vw;
  border-radius: 1.2vw;
  padding: 0.2vw;
  padding-top: 0.5vw;
  padding-left: 2vw;
  border: none;
}
.fa-magnifying-glass {
  position: absolute;
  top: 0.6vw;
  font-size: 1vw;
  left: 0.5vw;
}

#searchLogoutCont {
  display: flex;
  align-items: center;
}

.search-results {
    position: absolute;
    top: 100%;
    left: 0;
    z-index: 1000;
    background-color: white;
    border: 1px solid #ccc;
    border-top: none;
    border-radius: 0 0 5px 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    list-style: none;
    padding: 0;
    margin: 0;
}

.search-results li {
    padding: 10px;
    cursor: pointer;
}

.search-results li:hover {
    background-color: #f1f1f1;
}
</style>
