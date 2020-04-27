<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <h1>Request Activity</h1>
        <label for="name">Name</label>
        <input
          type="text"
          class="form-control"
          id="name"
          required
          v-model="event.name"
          name="name"
        />
      </div>
      <div class="form-group">
              <label for="timeSlot">Preferred Date</label>
                <datepicker v-model="event.timeSlot"></datepicker>
            </div>


      <button @click="saveEvent" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newEvent">Submit Another Event</button>
    </div>
  </div>
</template>

<script>
import EventDataService from "../services/EventDataService";
import Datepicker from 'vuejs-datepicker';
import { eventBus } from '../main.js';
export default {
  name: "add-event",
  components:{
    Datepicker
  },

  data() {
    return {
      event: {
        id: null,
        name: "",
        timeSlot:"",
        upVotes: 0
      },
      submitted: false
    };
  },
  methods: {
    saveEvent() {
      var data = {
        name: this.event.name,
        timeSlot: this.event.timeSlot,
        upVotes:this.event.upVotes
      };

      if(this.event.name==null||this.event.timeSlot==null)
      {
        alert("Please fill in all required fields...")
      }
      else{

      EventDataService.create(data)
        .then(response => {
          this.event.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
        }
    },

    newEvent() {
      this.submitted = false;
      this.event = {};
    }

  },
  mounted(){
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
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>