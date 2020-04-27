<template>
<div class = "schedule">
    <div class = "header">
        <h1 v-if="currentSchedule">{{this.currentSchedule.date}}</h1>
    </div>
    <div class = "friday" v-if="currentSchedule">
        <h2>Friday: </h2>
        <ul>
          <li v-for="(event, index) in currentSchedule.friday" :key="index">
              {{ event.timeSlot }} {{event.name}} <b-button v-if="event.signUp" @click = "signUpUser(event, 'friday', index)">Sign Up</b-button> <p v-else>Signed Up</p>
          </li>
        </ul>
    </div>
    <div class = "saturday" v-if="currentSchedule">
       <h2>Saturday: </h2>
       <ul>
            <li v-for="(event, index) in currentSchedule.saturday" :key="index">
               {{ event.timeSlot }} {{event.name}} <b-button v-if="event.signUp" @click="signUpUser(event, 'saturday', index)">Sign Up</b-button>
            </li>
       </ul>
    </div>
    <div class = "sunday" v-if="currentSchedule">
       <h2>Sunday: </h2>
        <ul>
             <li v-for="(event, index) in currentSchedule.sunday" :key="index">
                 {{ event.timeSlot }} {{event.name}} <b-button v-if="event.signUp" @click="signUpUser(event, 'sunday', index)">Sign Up</b-button>
             </li>
        </ul>
    </div>
</div>
</template>

<script>
import ScheduleDataService from '../services/ScheduleDataService'
export default{
    data(){
        return {
            currentSchedule:null
        };
    },
    computed:{

    },
    methods:{
        getSchedule(id){
            ScheduleDataService.get(id)
                .then(response => {
                  this.currentSchedule = response.data;
                  console.log(response.data);
                })
                .catch(e => {
                  console.log(e);
                });
        },
        signUpUser(event, date, index){
            if(date==='friday')
            {
                if(this.currentSchedule.friday[index].usersSignedUp==null)
                {
                    this.currentSchedule.friday[index].usersSignedUp=[]
                }
                event.usersSignedUp=this.currentSchedule.friday[index].usersSignedUp;
            }
            if(date==='saturday')
            {

                if(this.currentSchedule.saturday[index].usersSignedUp==null)
                {
                   this.currentSchedule.saturday[index].usersSignedUp=[]
                }
                event.usersSignedUp=this.currentSchedule.saturday[index].usersSignedUp;
            }
            if(date==='sunday')
            {
                if(this.currentSchedule.sunday[index].usersSignedUp==null)
                {
                    this.currentSchedule.sunday[index].usersSignedUp=[]
                }
               event.usersSignedUp=this.currentSchedule.sunday[index].usersSignedUp;
            }
            event.usersSignedUp.push(this.$cookies.get('user'))
            ScheduleDataService.update(this.currentSchedule.id, this.currentSchedule)
              .then(response => {
                console.log(response.data);
              })
              .catch(e => {
                console.log(e);
              });

        }
    },
    mounted(){
        this.getSchedule(this.$route.params.id)
    }
}
</script>
<style>
@import '../../public/schedule.css';
</style>