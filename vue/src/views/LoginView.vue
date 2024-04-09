<template>
  <div id="loginViewCont">
  <div id="login">
    <div id="formCont">
    <img src="../assets/imgs/Flashr-Logo.png" alt="" id="appLogo">
    <form v-on:submit.prevent="login">
      <h1 >Login</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <!-- <label for="username">Username</label> -->
        <input type="text" id="username" v-model="user.username" required autofocus placeholder="Username"/>
      </div>
      <div class="form-input-group">
        <!-- <label for="password">Password</label> -->
        <input type="password" id="password" v-model="user.password" required placeholder="Password"/>
      </div>
      <button class="btn" type="submit">Sign in</button>
      <button class="btn" type="submit" >
      <router-link style="text-decoration: none; color: inherit;" v-bind:to="{ name: 'register' }">Register</router-link></button>
    </form>
  </div>
    <img src="../assets/imgs/sign-in-logo.png" alt="Study Clip Art" id="signInLogo">

</div>
</div>
  
</template>

<script>
import authService from "../services/AuthService";
import Header from '../components/Header.vue';

export default {
  components: {
    
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
@font-face {
  font-family: 'Writing';
  src: url(../assets/Fonts/LovelexieHandwritten.ttf);
}
h1 {
  font-family: 'Writing';
  font-size: 2vw;
  margin: 1vw;
  padding: 0;
}

form {
  
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-items: center;
  
  
}
label {
  margin-right: 0.5rem;
}

#login {
  display: flex;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.6);
  background-color: rgb(255, 255, 255);
  width: 50%;
  border-radius: 2vw;
}
#signInLogo {
  width: 50%;
  background-color: rgb(107, 91, 250);
  border-radius: 2vw;
  box-shadow: rgb(114, 114, 114) 0px 0px 5px 2px inset;
  margin: .2vw;
}

#appLogo {
  width: 40%;
  margin-top: 1vw;
}

#formCont {
  width: 50%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.btn {
  display: block;
  width: 80%;
  height: 2.5vw;
  margin-top: .6vw;
  border: none;
  background-color: rgb(65, 65, 253);
  border-radius: .3vw;
  color: white;
  font-size: 1.5vw;
  text-decoration: none;
  font-family: 'Writing';
}
input {
  font-size: 1.5vw;
  padding: .5vw;
  border-radius: .3vw;
  border: none;
  height: auto;
  width: 18vw;
  font-family: 'Writing';
  margin-bottom: .5vw;
}

#loginViewCont {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url(src/assets/imgs/Sign-in-background.png);
}

</style>