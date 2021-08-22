import Vue from "vue";
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    count: 0,
    token: null
  },
  mutations: {
    increment (state) {
      state.count++
    },
    LOGIN_SUCCESS(state, response) {
      state.token = response.data.token
    }
  }
})

