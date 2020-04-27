<template>
<div class = "body">
  <div class="list row">
    <div class="col-md-8">
    <h1 v-if="userNotNull">hello,{{currentUser.userName}}</h1>
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by name"
          v-model="name"/>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Requested Events</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(event, index) in filteredEvents"
          :key="index"
          @click="setActiveEvent(event, index)"
        >
          {{ event.name }}
        </li>
      </ul>

    </div>
    <div class="col-md-6">
          <div v-if="currentEvent">
            <h4>Event</h4>
            <div>
              <label><strong>Name:</strong></label> {{ currentEvent.name }}
            </div>
            <div>
              <label><strong>Requested Date:</strong></label> {{ currentEvent.timeSlot }}
            </div>


            <div class="wrap">
              <div class="votes">{{currentEvent.upVotes}}</div>
              <div class="button" v-on:click="down" v-if="upVoteExists"><i class="fa fa-arrow-up"></i>UP VOTED!!!</div>
              <div class="button" v-on:click="upvote" v-else><i class="fa fa-arrow-up"></i>VOTE THIS EVENT UP</div>


            </div>
            <a class="badge badge-warning"
              :href="'/events/' + currentEvent.id" v-if="admin"
            >
              Edit
            </a>
          </div>
          <div v-else>
            <br />
            <p>Click on a Event to show its information...</p>
          </div>
        </div>
  </div>
</div>
</template>

<script>
import EventDataService from "../services/EventDataService";
import UserDataService from "../services/UserDataService";
import Vue from 'vue'
import VueCookies from 'vue-cookies'
Vue.use(VueCookies)
import { eventBus } from '../main.js';

export default {
  name: "event-list",
  data() {
    return {
      events: [],
      users: [],
      currentUser:null,
      currentEvent: null,
      currentIndex: -1,
      name: "",
      index:-1,
      upvotes:[],
      admin:this.$cookies.get('admin')


    };
  },
  computed: {
      filteredEvents() {

        return this.events.filter(event => {
          return event.name.toLowerCase().includes(this.name.toLowerCase())
        })
       },
       upVoteExists(){
          for(var i = 0; i < this.currentUser.upvotes.length; i++){
            if(this.currentUser.upvotes[i].name===this.currentEvent.name)
            {
                return true;
            }
          }
          return false;
       },
       userNotNull(){
        if(this.$cookies.get('user')==null)
        {
            return false;
        }
        return true;
       }



  },
  methods: {
    getCurrentUser(){
         this.currentUser=this.$cookies.get('user')
    },
    retrieveEvents() {
      EventDataService.getAll()
        .then(response => {
          this.events = response.data;
          console.log(response.data);
        })


        .catch(e => {
          console.log(e);
        });
    },
    retrieveUsers() {
       UserDataService.getAll()
                .then(response => {
                  this.users = response.data;
                  console.log(response.data);
                })
                .catch(e => {
                  console.log(e);
                });
    },
    upvote(){
        this.currentEvent.upVotes++;
        EventDataService.update(this.currentEvent.id, this.currentEvent)
        this.currentUser.upvotes.push(this.currentEvent);
        console.log(this.currentUser.upvotes)
        UserDataService.update(this.currentUser.id, this.currentUser)

    },
    down(){
        this.currentEvent.upVotes--;
        EventDataService.update(this.currentEvent.id, this.currentEvent)
        for(var i = 0; i < this.currentUser.upvotes.length; i++) {
            if(this.currentUser.upvotes[i].id == this.currentEvent.id) {
                this.currentUser.upvotes.splice(i, 1);
            }
        }
        console.log(this.currentUser.upvotes)
        UserDataService.update(this.currentUser.id, this.currentUser)
    },



    refreshList() {
      this.retrieveEvents();
      this.currentEvent = null;
      this.currentIndex = -1;
    },

    setActiveEvent(event, index) {
      this.currentEvent = event;
      this.currentIndex = index;

    },

  },

  mounted() {
    this.retrieveEvents();
    this.retrieveUsers();
    if(this.$cookies.get('user')==null)
    {
       alert("Sign in to access this page")
       this.$router.push('home')
    }
    if(this.$cookies.get('admin')=="true"){
       eventBus.$emit('adminSet', true);
    }
    if(this.$cookies.get('admin')=="false"){
       eventBus.$emit('adminSet', false);
    }


  },
  beforeMount(){
      this.getCurrentUser();
  }
};

</script>

<style>

@import '../../public/stylingvue.css';

.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}


</style>
