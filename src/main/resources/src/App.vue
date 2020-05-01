<template>
  <div id="app">
    <b-navbar toggleable="lg" type="dark" variant="dark">
        <b-navbar-brand href="/home"><img class = "image" src="../public/img/logo.svg"></b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item href="/events">Events</b-nav-item>
            <b-nav-item href="/add">Request Event</b-nav-item>
            <b-nav-item :href="'/schedules/'+ $cookies.get('latestId')">View Schedule</b-nav-item>
            <b-nav-item v-if="admin" href="/schedules">All Schedules</b-nav-item>
          </b-navbar-nav>

          <b-navbar-nav class="ml-auto">

          </b-navbar-nav>
        </b-collapse>
    </b-navbar>

    <div>
        <router-view />
    </div>


  </div>
</template>

<script>
import { eventBus } from './main.js';
import Vue from 'vue'
import VueCookies from 'vue-cookies'
Vue.use(VueCookies)
import BootstrapVue from 'bootstrap-vue'
Vue.use(BootstrapVue)
export default{
    data(){
        return {
            admin: this.$cookies.get('admin')
        }
    },
    computed:{
    },
    methods:{

    },
    created() {
        eventBus.$on('adminSet', (data) => this.admin = data);
        console.log(this.admin)
    }
}
</script>
<style>
@media only screen and (max-width: 415px) {
    .image{
        width:200px;
    }
}

.nav-item{

    font-family: 'Clarkson',Helvetica,sans-serif;
    text-transform:uppercase;


}
</style>

