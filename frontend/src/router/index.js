import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Boiler61 from "../components/Boiler61";
import Message from "../components/Message";
import Login from "../components/Login";

Vue.use(Router)

export default new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // },
    // {
    //   path: '/',
    //   name: 'Boiler61',
    //   component: Boiler61
    // },
    // {
    //   path: '/',
    //   name: 'Message',
    //   component: Message
    // }
    {
      path: '/',
      name: 'Login',
      component: Login
    }
  ]
})
