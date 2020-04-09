<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
       <a href="https://www.lfanet.org/" class="logo"><image src = "../public/img/logo.svg" class = "logo"></image></a>
      <div class="navbar-nav mr-auto">
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
import EventList from './components/EventList.vue';
import Home from './components/Home.vue';
export default {
  name: 'app',
  components: {
      EventList, Home
  }
}
</script>