<template>
    <div id="logo" v-on:click.prevent="goHome">
        <img src="src\assets\Flashr-Logo.png"/>
    </div>
    <label>Search</label>
    <input id="header-search-bar" type="text">
</template>
  
  <script>
  export default {
    methods: {
        goHome(){
            this.$router.push("/");
        }
    }
  };
  </script>
  
  <style scoped>
 
  </style>
  