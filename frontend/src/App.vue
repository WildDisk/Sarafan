<template>
  <div id="app">
    <!--    <v-parallax height="300"-->
    <!--                src="https://images.squarespace-cdn.com/content/v1/569c3354b204d58bba6f0a81/1574218361110-VG1OWTMDO14MGU74AZWB/image-asset.gif"/>-->
        <v-app>
          <router-view/>
        </v-app>
<!--    <div v-if="!profile">Необходимо авторизоваться через-->
<!--      <a href="/login">Google</a>-->
<!--    </div>-->
<!--    <div v-else>-->
<!--      <div>{{ profile.name }}&nbsp;-->
<!--        <a href="/logout">Выйти</a>-->
<!--      </div>-->
<!--&lt;!&ndash;      <message-list :messages="messages"/>&ndash;&gt;-->
<!--    </div>-->
  </div>
</template>

<script>
import Boiler61 from "./components/Boiler61";
import Message from "./components/Message";

export default {
  name: 'App',
  data: {
  },
  components: {Message, Boiler61}
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
