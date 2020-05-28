<template>
<div class = "schedule-body" v-if = "!submitted">
<div class = "container">
<div class = "date">
    <h1>Date Range of Weekend</h1>
    <input type="text" class = "form-control" v-model="schedule.date" placeholder="format:'Month Date-Date'">
</div>
<div class = "friday">
    <h1>Friday</h1>
    <div v-for="(event, index) in schedule.friday" :key="index">
        Name
        <input type="text" class="form-control" v-model="event.name" placeholder="e.x. SuperTarget Run">
        Time Slot
        <input type="text" class="form-control" v-model="event.timeSlot" placeholder="format-'5:00-6:00pm'">
        <input type="checkbox" :id="event.name" v-model="event.signUp">
        <label :for="event.name">Require Sign Up</label>
        <div v-if="event.signUp">
            Person Limit
            <input type="text" class="form-control" v-model="event.personLimit" placeholder="e.x. 11">
        </div>
    </div>
    <b-button @click="addNewEventFri">+ Add New Event</b-button>
    <b-button variant="danger" @click="deleteEventFri">- Delete Event</b-button>
</div>
<div class = "saturday">
    <h1>Saturday</h1>
    <div v-for="(event, index) in schedule.saturday" :key="index">
        Name
        <input type="text" class="form-control" v-model="event.name" placeholder="e.x. Karaoke">
        Time Slot
        <input type="text" class="form-control" v-model="event.timeSlot" placeholder="format-'4:00-7:00pm'">
        <input type="checkbox" :id="event.name" v-model="event.signUp">
        <label :for="event.name">Require Sign Up</label>
        <div v-if="event.signUp">
            Person Limit
            <input type="text" class="form-control" v-model="event.personLimit" placeholder="e.x. 11">
        </div>
    </div>
    <b-button @click="addNewEventSat">+ Add New Event</b-button>
    <b-button variant="danger" @click="deleteEventSat">- Delete Event</b-button>
</div>
<div class = "sunday">
    <h1>Sunday</h1>
    <div v-for="(event, index) in schedule.sunday" :key="index">
        Name
        <input type="text" class="form-control" v-model="event.name" placeholder="e.x. Museum Visit">
        Time Slot
        <input type="text" class="form-control" v-model="event.timeSlot" placeholder="format-'11:00-12:00am'">
        <input type="checkbox" :id="event.name" v-model="event.signUp">
        <label :for="event.name">Require Sign Up</label>
        <div v-if="event.signUp">
            Person Limit
            <input type="text" class="form-control" v-model="event.personLimit" placeholder="e.x. 11">
        </div>
    </div>
    <b-button @click="addNewEventSun">+ Add New Event</b-button>
    <b-button variant="danger" @click="deleteEventSun">- Delete Event</b-button>
</div>
<div class = "submit">
    <b-button @click = "submit" variant="success">Submit</b-button>
</div>
</div>
</div>
<div class = "container" v-else>
    <h1>You submitted a schedule successfully!</h1>
    <b-button :href="'/schedules/' + schedule.id">View Schedule</b-button>
    <b-button href="/schedules">Go Back to View All Schedules</b-button>
</div>
</template>

<script>
import ScheduleDataService from "../services/ScheduleDataService";
export default {
    data(){
        return {
          schedule: {
            id: null,
            date: "",
            friday:[],
            saturday:[],
            sunday:[]
          },
          submitted: false
        };
    },
    computed:{

    },
    methods:{

         addNewEventFri(){
            this.schedule.friday.push({name:'',timeSlot:'', usersSignedUp:[]});
            console.log(this.schedule.friday)
         },
         addNewEventSat(){
            this.schedule.saturday.push({name:'',timeSlot:'', usersSignedUp:[]});
            console.log(this.schedule.saturday)
         },
         addNewEventSun(){
            this.schedule.sunday.push({name:'',timeSlot:'', usersSignedUp:[]});
            console.log(this.schedule.sunday)
         },
         deleteEventFri(){
            if(this.schedule.friday.length>0)
            {
                this.schedule.friday.pop();
                console.log(this.schedule.friday)
            }
         },
         deleteEventSat(){
            if(this.schedule.saturday.length>0)
            {
                this.schedule.saturday.pop();
                console.log(this.schedule.saturday)
            }
         },
         deleteEventSun(){
            if(this.schedule.sunday.length>0)
            {
                 this.schedule.sunday.pop();
                console.log(this.schedule.sunday)
            }
         },
         submit(){
                if(!confirm("Are you sure you want to submit? Check all of the input boxes..."))
                {
                    return;
                }
                var data ={
                    date:this.schedule.date,
                    friday:this.schedule.friday,
                    saturday:this.schedule.saturday,
                    sunday:this.schedule.sunday
                }
                ScheduleDataService.create(data)
                    .then(response => {
                       this.schedule.id = response.data.id;

                       this.$cookies.set('latestId', this.schedule.id)
                       this.schedule=response.data;
                       console.log(response.data);
                       this.submitted=true;

                    })
                    .catch(e => {
                      console.log(e);
                      });


         }
    },

    mounted(){
        if(this.$cookies.get('user').admin==false)
        {
           alert("Sign in as an admin to access this page...")
           this.$router.push('home')
        }

    }

}
window.onbeforeunload = () => 'Are you sure you want to leave?'
</script>
<style>
@import '../../public/schedule.css';
</style>