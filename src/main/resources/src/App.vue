<template>
  <div id="app">
    <b-navbar toggleable="lg" type="dark" variant="dark">
        <b-navbar-brand href="/home"><img class = "image" src="../public/img/logo.svg"></b-navbar-brand>

<<<<<<< HEAD
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
=======
      <div class="navbar-nav mr-auto">
        <div class = "image">
         <a href="/home" class="pull-left"><img src="../public/img/logo.svg"></a>
         </div>
         <li class="nav-item">
            <a href="/events" class="nav-link">Events</a>
         </li>

        <li class="nav-item">
            <a href="/add" class="nav-link">Request Event</a>
        </li>
        <li class="nav-item">
            <a href="/eventSignUp" class="nav-link">Event Sign Up</a>
         </li>
      </div>
    </nav>
>>>>>>> fb1ba08a534df07f0e6aeda92b72245f1f8eff31

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
<<<<<<< HEAD
=======
        signedIn(){
            if(this.$cookies.get('user')==null)
            {
                return false;
            }
            return true;
        }
>>>>>>> fb1ba08a534df07f0e6aeda92b72245f1f8eff31
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
<<<<<<< HEAD
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
=======
.nav-item{
    width: 230px;
    padding-top: 2%;
    text-align:center;
    font-size:25px;
    margin-bottom: -14px;
}
.nav-item:hover{
    border-bottom: 13px solid orange;

}
.image{
    vertical-align: middle;
    height:75%;
    margin-top: 16px;
    margin-bottom: 16px;
    margin-left: 10px;
    margin-right: 48.5px;
    border-style: none;
}
</style>
>>>>>>> fb1ba08a534df07f0e6aeda92b72245f1f8eff31
