<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">

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

    <div>
        <router-view />
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import VueCookies from 'vue-cookies'
Vue.use(VueCookies)
import BootstrapVue from 'bootstrap-vue'
Vue.use(BootstrapVue)
export default{
    data(){
        return {

        }
    },
    computed:{
        signedIn(){
            if(this.$cookies.get('user')==null)
            {
                return false;
            }
            return true;
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