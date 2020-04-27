<template>
  <div v-if="currentEvent" class="edit-form">
    <h4>Event</h4>
    <form>
      <div class="form-group">
        <label for="name">Event</label>
        <input type="text" class="form-control" id="name"
          v-model="currentEvent.name"
        />
      </div>
      <div class="form-group">
        <label for="timeSlot">Time Slot</label>
        <input type="text" class="form-control" id="timeSlot"
          v-model="currentEvent.timeSlot"
        />
      </div>



    </form>
    <button class="badge badge-danger mr-2"
      @click="deleteEvent"
    >
      Delete
    </button>

    <button type="submit" class="badge badge-success"
      @click="updateEvent"
    >
      Update Event
    </button>
    <p>{{ message }}</p>
  </div>

</template>

<script>
import EventDataService from "../services/EventDataService";
import { eventBus } from '../main.js';
export default {
  name: "event",
  data() {
    return {
      currentEvent: null,
      message: ''
    };
  },
  methods: {
    getEvent(id) {
      EventDataService.get(id)
        .then(response => {
          this.currentEvent = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    updateEvent() {
      EventDataService.update(this.currentEvent.id, this.currentEvent)
        .then(response => {
          console.log(response.data);
          this.message = 'The event was updated successfully!';
        })
        .catch(e => {
          console.log(e);
        });
    },

    deleteEvent() {
      EventDataService.delete(this.currentEvent.id)
        .then(response => {
          console.log(response.data);
          this.$router.push({ name: "events" });
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getEvent(this.$route.params.id);
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
       alert("Admin permission denied...")
       this.$router.push('/events')
    }
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
