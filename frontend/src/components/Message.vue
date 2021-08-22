<template>
  <div>
    <v-toolbar>
      <v-toolbar-title>
        <h2>Messages</h2>
      </v-toolbar-title>
    </v-toolbar>
    <v-data-table
      :headers="headers"
      :items="messages.data"
      class="elevation-0">
      <template v-slot:no-data>
        <v-alert :value="true" color="error" icon="warning">
          Сорян! ¯\_(ツ)_/¯<br/>Чёт пошло не так...
        </v-alert>
      </template>
    </v-data-table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Message",
  data() {
    return {
      headers: [
        {text: 'Id', value: 'id'},
        {text: 'Title', value: 'title'},
        {text: 'Text', value: 'text'},
        {text: 'Creation Date Time', value: 'creationDateTime'}
      ],
      messages:[]
    }
  },
  mounted() {
    axios
    .get("https://localhost:8000")
    .then(response => { this.boiler = response })
    .catch(error => console.log(error))
  }
}
</script>

<style scoped>

</style>
