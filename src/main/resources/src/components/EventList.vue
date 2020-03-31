<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by name"
          v-model="name"/>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Events List</h4>
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

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllEvents">
        Remove All
      </button>
    </div>
    <div class="col-md-6">
          <div v-if="currentEvent">
            <h4>Event</h4>
            <div>
              <label><strong>Name:</strong></label> {{ currentEvent.name }}
            </div>
            <div>
              <label><strong>Time Slot:</strong></label> {{ currentEvent.timeSlot }}
            </div>
            <div>
               <label><strong>Up Votes:</strong></label> {{ currentEvent.upVotes }}
             </div>

            <button @click="upvote">Up Vote</button>
            <a class="badge badge-warning"
              :href="'/events/' + currentEvent.id"
            >
              Edit
            </a>
          </div>
          <div v-else>
            <br />
            <p>Please click on a Event...</p>
          </div>
        </div>
  </div>
</template>

<script>
import EventDataService from "../services/EventDataService";

export default {
  name: "events-list",
  data() {
    return {
      events: [],
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
    upvote(){
        this.currentEvent.upVotes++;
        EventDataService.update(this.currentEvent.id, this.currentEvent)
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

    removeAllEvents() {
      EventDataService.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.log(e);
        });
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
