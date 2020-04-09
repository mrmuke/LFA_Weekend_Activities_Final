<template>
  <div class="list row">
    <div class="col-md-8">

    <h1>hello,{{emailAddress}}</h1>
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by name"
          v-model="name"/>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Requested Events List</h4>
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
              <div class="button" v-on:click="upvote" v-if="currentEvent.upVoted"><i class="fa fa-arrow-up"></i>UP VOTED!!!</div>
              <div class="button" v-on:click="upvote" v-else><i class="fa fa-arrow-up"></i>VOTE THIS EVENT UP</div>
            </div>
            <a class="badge badge-warning"
              :href="'/events/' + currentEvent.id"
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
</template>

<script>
import EventDataService from "../services/EventDataService";
import UserDataService from "../services/UserDataService";
export default {
  name: "event-list",
  data() {
    return {
      events: [],
      users: [],
      currentUserName:"",
      emailAddress:"",
      currentEvent: null,
      currentIndex: -1,
      name: ""

    };
  },
  computed: {
      filteredEvents() {

        return this.events.filter(event => {
          return event.name.toLowerCase().includes(this.name.toLowerCase())
        })
       }



  },
  methods: {
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
        this.currentEvent.upVoted = !this.currentEvent.upVoted;
        this.currentEvent.message = !this.currentEvent.message;
        EventDataService.update(this.currentEvent.id, this.currentEvent)
        if(this.currentEvent.upVoted == true)
        {
            this.currentEvent.upVotes++;


        }
        else
        {
            this.currentEvent.upVotes--;

        }
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



    searchName() {

      EventDataService.findByName(this.name)
        .then(response => {
          this.events = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.retrieveEvents();
    this.retrieveUsers();

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
