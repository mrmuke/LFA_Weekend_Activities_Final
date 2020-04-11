<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">

      <div class="navbar-nav mr-auto">
         <a href="/home" class="pull-left"><img src="../public/img/logo.svg"></a>
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