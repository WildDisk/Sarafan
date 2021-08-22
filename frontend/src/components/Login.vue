<template>
  <v-form>
    <div>{{ hello.data }}</div>
    <div>{{ hello }}</div>
    <div>{{ user }}</div>
    <div>{{ token }}</div>
    <div>{{ this.$store.state.count }}</div>
    <div>{{ this.$store.state.token }}</div>
    <!--    <p><v-text-field name="username" aria-placeholder="username"></v-text-field></p>-->
    <!--    <p><v-text-field name="password" type="password" aria-placeholder="password"></v-text-field></p>-->
    <v-btn @click="send">OK</v-btn>
    <!--    <v-text-field v-model="message" label="message"/>-->
    <!--    <v-btn @click="sendMessage">Send</v-btn>-->
    <v-btn @click="takeUser">User</v-btn>
  </v-form>
</template>

<script>
import axios from "axios";

axios.defaults.baseURL = 'http://localhost:8000/'
axios.defaults.headers.common = {
  'Access-Control-Allow-Origin': '*',
  'Content-Type': 'application/json',
  'Authorization': `Bearer ${this.token}`
}
export default {
  name: "Login",
  props: {
    employee: Object
  },
  data() {
    return {
      username: "username",
      password: "password",
      hello: "",
      message: "",
      user: {
        name: "",
        counter: -1
      },
      token: "",
      authorization: {Authorization: `Bearer ` + this.token}
    }
  },
  mounted() {
    axios
      .get("/")
      .then(response => {
        console.log(response)
        this.hello = response
      })
      .catch(error => console.log(error))
    // axios
    // .get("http://localhost:8000/user")
    // .then(response => this.user = response.data)
    // .catch(error => console.log(error))
  },
  methods: {
    send() {
      axios
        .post("/auth",
          {
            username: this.username,
            password: this.password
          }
        )
        .then(response => {
          this.$store.commit('LOGIN_SUCCESS', response)
        })
        .catch(error => console.log(error))
    },
    sendMessage() {
      const headers = {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json'
      }
      axios
        .post("http://localhost:8000/send", {text: this.message}, {headers})
        .catch(error => console.log(error))
    },
    takeUser() {
      axios
        .get("/userRegistred", {
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${this.$store.state.token}`
          }
        })
        .then(response => this.user = response.data)
        .catch(result => console.log(result))
    }
  }
}
</script>

<style scoped>

</style>
