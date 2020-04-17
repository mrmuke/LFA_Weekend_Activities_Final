<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">

      <div class="navbar-nav mr-auto">
         <a href="/home" class="pull-left"><img src="../public/img/logo.svg"></a>
         <li class="nav-item">
                   <a href="#" class="nav-link" @click="alert" v-if="notSignedIn">Events</a>
                   <a href="/events" class="nav-link" v-else>Events</a>
         </li>

        <li class="nav-item">
                    <a href="#" class="nav-link" @click="alert" v-if="notSignedIn">Request Event</a>
                    <a href="/add" class="nav-link" v-else>Request Event</a>
        </li>
        <li class="nav-item">
            <a href="#" class="nav-link" @click="alert" v-if="notSignedIn">Event Sign Up</a>
            <a href="/eventSignUp" class="nav-link" v-else>Event Sign Up</a>
         </li>
      </div>
    </nav>

    <div>
        <router-view />
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import VueCookies from 'vue-cookies'
Vue.use(VueCookies)
export default{
    data(){
        return {

        }
    },
    computed:{
        notSignedIn(){
            if(this.$cookies.get('user')==null)
            {
                return true;
            }
            return false;
        }
    },
    methods:{
       alert(){
          alert("Please sign in before you click these buttons...")
       }
    },
    mounted(){
    }
}
</script>

<style>
.nav-item{
    padding-left:10px;
    text-align:center;
    font-size:30px;
}
.nav-item:hover{
    background-color: orange;
    color:white;
}

</style>