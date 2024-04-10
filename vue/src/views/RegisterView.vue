<template>
  <div id="registerViewCont">
    
    <div id="register" class="text-center">
      <div id="formCont">
        <img src="../assets/imgs/Flashr-Logo.png" alt="" id="appLogo" />
        <form v-on:submit.prevent="register">
          <h1>Create Account</h1>
          <div role="alert" v-if="registrationErrors">
            {{ registrationErrorMsg }}
          </div>
          <div class="form-input-group">
            <!-- <label for="username">Username</label> -->
            <input
              type="text"
              id="username"
              v-model="user.username"
              required
              autofocus
              placeholder="Username"
            />
          </div>
          <div class="form-input-group">
            <!-- <label for="password">Password</label> -->
            <input
              type="password"
              id="password"
              v-model="user.password"
              required
              placeholder="Password"
            />
          </div>
          <div class="form-input-group">
            <!-- <label for="confirmPassword">Confirm Password</label> -->
            <input
              type="password"
              id="confirmPassword"
              v-model="user.confirmPassword"
              required
              placeholder="Confirm Password"
            />
          </div>
          <button type="submit" class="btn">Create Account</button>
          <button class="btn">
            <router-link
              v-bind:to="{ name: 'login' }"
              style="text-decoration: none; color: inherit"
              >Back to Login</router-link
            >
          </button>
        </form>
      </div>
      <img src="../assets/imgs/Registration.png" id="registerLogo" alt="" />
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import Header from "../components/Header.vue";

export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  components: {
    Header,
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
@font-face {
  font-family: "Writing";
  src: url(../assets/Fonts/LovelexieHandwritten.ttf);
}
h1 {
  font-family: "Writing";
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

#register {
  display: flex;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.6);
  background-color: rgb(255, 255, 255);
  width: 50%;
  border-radius: 2vw;
}
#registerLogo {
  width: 50%;
  background-color: rgb(107, 91, 250);
  border-radius: 2vw;
  box-shadow: rgb(114, 114, 114) 0px 0px 5px 2px inset;
  margin: 0.2vw;
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
  margin-top: 0.6vw;
  border: none;
  background-color: rgb(65, 65, 253);
  border-radius: 0.3vw;
  color: white;
  font-size: 1.5vw;
  text-decoration: none;
  font-family: "Writing";
}
input {
  font-size: 1.5vw;
  padding: 0.5vw;
  border-radius: 0.3vw;
  border: none;
  height: auto;
  width: 18vw;
  font-family: "Writing";
  margin-bottom: 0.5vw;
}

#registerViewCont {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url(src/assets/imgs/Sign-in-background.png);
}
</style>
