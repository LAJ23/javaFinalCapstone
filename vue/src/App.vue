<template>
  <Header v-if="showHeader" />
  <div id="capstone-app">

    <router-view />
  </div>
</template>
<script>
import Header from '../src/components/Header.vue'; 

export default {
  components: {
    Header
  },
  computed: {
    showHeader() {
      return this.$route.meta.showHeader; 
    }
  }
}
</script>
<style>
* {
  margin: 0;
}
#capstone-app {
  
  height: 100%;
}</style>
