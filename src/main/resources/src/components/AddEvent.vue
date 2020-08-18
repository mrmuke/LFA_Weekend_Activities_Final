<template>
<div class = "submit-body">
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group" style="max-width:300px">
        <h1>Request Activity</h1>
        <label for="name">Name</label>
        <input
          type="text"
          class="form-control"
          id="name"
          required
          v-model="event.name"
          name="eventName"
          placeholder="Be specific..."
        />
      </div>
      <div class="form-group">
              <label for="timeSlot">Preferred Date</label>
                <datepicker :disabledDates="disabledDates" v-model="event.timeSlot"></datepicker>
            </div>


      <button class="default" @click="saveEvent">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="default" @click="newEvent">Submit Another Event</button>
    </div>
  </div>
</div>
</template>

<script>
import EventDataService from "../services/EventDataService";
import Datepicker from 'vuejs-datepicker';
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
      events:[],
      submitted: false,
      disabledDates: {
          to: new Date()
      }

    };
  },
  methods: {
    saveEvent() {
      for(var i=0;i<this.events.length;i++)
      {
          if(this.events[i].name ===this.event.name)
          {
            alert("An event by this name already exists...")
            return;
          }
      }
      var data = {
        name: this.event.name,
        timeSlot: this.event.timeSlot,
        upVotes:this.event.upVotes,
        requested:this.$cookies.get('user')
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
    getEvents(){
        EventDataService.getAll()
          .then(response => {
             this.events = response.data;
             console.log(response.data);
          })


          .catch(e => {
            console.log(e);
          });
    },

    newEvent() {
      this.submitted = false;
      this.event = {};
    },

    },


  mounted(){
    if(this.$cookies.get('user')==null)
    {
        alert("Sign in to access this page")
        this.$router.push('home')
    }


  }
};
</script>

<style>
@import '../../public/stylingvue.css';
.submit-form {
  width: 1000px;
  margin: auto;

}

</style>