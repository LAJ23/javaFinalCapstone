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
        >Logout</router-link
      >
      <div id="searchBar">
        <div class="input-icon">
          <i class="fa-solid fa-magnifying-glass"></i>
          <input type="text" class="input-field" placeholder="Search" />
        </div>
      </div>
    </div>
  </header>

  <div id="logo" v-on:click.prevent="goHome">
    <img src="src\assets\Flashr-Logo.png" />
  </div>
  <label>Search</label>
  <input id="header-search-bar" type="text" />
</template>

<script>
export default {
  methods: {
    goHome() {
      this.$router.push("/");
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
</style>
