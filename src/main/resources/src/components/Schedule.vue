<template>
<<<<<<< HEAD
<div>
    <div class = "header">
        <h1 v-if="currentSchedule">{{this.currentSchedule.date}}</h1>
    </div>
    <div class = "moveToMiddle" v-if="currentSchedule" >
        <h2>Friday: </h2>
        <ul>
          <li v-for="(event, index) in currentSchedule.friday" :key="index">
              {{ event.timeSlot }} {{event.name}} <p v-if="!event.signUp"></p><b-button v-else-if="notSignedUp(event)" @click = "signUpUser(event, 'friday', index)">Sign Up</b-button> <b-button v-else @click="removeFromList(event)">Signed Up (No.{{place(event)}} on the List)</b-button>
          </li>
        </ul>
    </div>
    <div class = "moveToMiddle" v-if="currentSchedule">
       <h2>Saturday: </h2>
       <ul>
            <li v-for="(event, index) in currentSchedule.saturday" :key="index">
               {{ event.timeSlot }} {{event.name}} <p v-if="!event.signUp"></p><b-button v-else-if="notSignedUp(event)" @click="signUpUser(event, 'saturday', index)">Sign Up</b-button> <b-button v-else @click="removeFromList(event)">Signed Up (No.{{place(event)}} on the List)</b-button>
            </li>
       </ul>
    </div>
    <div class = "moveToMiddle" v-if="currentSchedule">
       <h2>Sunday: </h2>
        <ul>
             <li v-for="(event, index) in currentSchedule.sunday" :key="index">
                 {{ event.timeSlot }} {{event.name}} <p v-if="!event.signUp"></p> <b-button v-else-if="notSignedUp(event)" @click="signUpUser(event, 'sunday', index)">Sign Up</b-button> <b-button v-else @click="removeFromList(event)">Signed Up (No.{{place(event)}} on the List)</b-button>
=======
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
>>>>>>> fb1ba08a534df07f0e6aeda92b72245f1f8eff31
             </li>
        </ul>
    </div>
</div>
</template>

<script>
<<<<<<< HEAD
import { eventBus } from '../main.js';
=======
>>>>>>> fb1ba08a534df07f0e6aeda92b72245f1f8eff31
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
<<<<<<< HEAD
        place(event){
            for(var i = 0; i<event.usersSignedUp.length;i++)
            {
                if(event.usersSignedUp[i].userName===this.$cookies.get('user').userName)
                {
                    return i+1;
                }
            }
        },
        removeFromList(event)
        {
             for(var i = 0;i<event.usersSignedUp.length;i++)
             {
                if(event.usersSignedUp[i].userName===this.$cookies.get('user').userName)
                {
                   event.usersSignedUp.splice(i,1);
                }
            }
            ScheduleDataService.update(this.currentSchedule.id, this.currentSchedule)
                .then(response => {
                  console.log(response.data);
                })
                .catch(e => {
                  console.log(e);
                });

        },
        notSignedUp(event){
            var notSignedUp=true;
            for(var i = 0;i<event.usersSignedUp.length;i++)
            {
               if(event.usersSignedUp[i].userName===this.$cookies.get('user').userName)
               {
                   notSignedUp=false
               }
            }
            console.log(notSignedUp)
            return event.signUp && notSignedUp
        },
=======
>>>>>>> fb1ba08a534df07f0e6aeda92b72245f1f8eff31
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
<<<<<<< HEAD
=======
                if(this.currentSchedule.friday[index].usersSignedUp==null)
                {
                    this.currentSchedule.friday[index].usersSignedUp=[]
                }
>>>>>>> fb1ba08a534df07f0e6aeda92b72245f1f8eff31
                event.usersSignedUp=this.currentSchedule.friday[index].usersSignedUp;
            }
            if(date==='saturday')
            {
<<<<<<< HEAD
=======

                if(this.currentSchedule.saturday[index].usersSignedUp==null)
                {
                   this.currentSchedule.saturday[index].usersSignedUp=[]
                }
>>>>>>> fb1ba08a534df07f0e6aeda92b72245f1f8eff31
                event.usersSignedUp=this.currentSchedule.saturday[index].usersSignedUp;
            }
            if(date==='sunday')
            {
<<<<<<< HEAD
=======
                if(this.currentSchedule.sunday[index].usersSignedUp==null)
                {
                    this.currentSchedule.sunday[index].usersSignedUp=[]
                }
>>>>>>> fb1ba08a534df07f0e6aeda92b72245f1f8eff31
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
<<<<<<< HEAD
        if(this.$cookies.get('admin')=="true"){
           eventBus.$emit('adminSet', true);
        }
        if(this.$cookies.get('admin')=="false"){
           eventBus.$emit('adminSet', false);
        }
=======
>>>>>>> fb1ba08a534df07f0e6aeda92b72245f1f8eff31
    }
}
</script>
<style>
@import '../../public/schedule.css';
</style>