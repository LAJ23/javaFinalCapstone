<template>
  <Header/>
  <h1>Logout</h1>
</template>

<script>
import Header from "../components/Header.vue"
export default {
  components: {
    Header
  },
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
  }
};
</script>
