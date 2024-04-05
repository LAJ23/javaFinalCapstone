<template>
  <Header/>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
  </div>
</template>

<script>
import Header from '../components/Header.vue';


export default {
  components: {
    Header
  }
};
</script>
