<template>
<div class = "schedule-body">
    <div class = "header">
        <h1 v-if="currentSchedule">{{this.currentSchedule.date}}</h1>
    </div>
    <div v-if="currentSchedule">
        <h2>Friday: </h2>
        <ul>
          <li v-for="(event, index) in currentSchedule.friday" :key="index">
              {{ event.timeSlot }} {{event.name}} <p v-if="!event.signUp"></p><b-button v-else-if="notSignedUp(event)" @click = "signUpUser(event, 'friday', index)">Sign Up</b-button> <b-button v-else @click="removeFromList(event)">Signed Up (No.{{place(event)}} on the List)</b-button>
          </li>
        </ul>
    </div>
    <div v-if="currentSchedule">
       <h2>Saturday: </h2>
       <ul>
            <li v-for="(event, index) in currentSchedule.saturday" :key="index">
               {{ event.timeSlot }} {{event.name}} <p v-if="!event.signUp"></p><b-button v-else-if="notSignedUp(event)" @click="signUpUser(event, 'saturday', index)">Sign Up</b-button> <b-button v-else @click="removeFromList(event)">Signed Up (No.{{place(event)}} on the List)</b-button>
            </li>
       </ul>
    </div>
    <div v-if="currentSchedule">
       <h2>Sunday: </h2>
        <ul>
             <li v-for="(event, index) in currentSchedule.sunday" :key="index">
                 {{ event.timeSlot }} {{event.name}} <p v-if="!event.signUp"></p> <b-button v-else-if="notSignedUp(event)" @click="signUpUser(event, 'sunday', index)">Sign Up</b-button> <b-button v-else @click="removeFromList(event)">Signed Up (No.{{place(event)}} on the List)</b-button>
             </li>
        </ul>
    </div>
    <div v-if="$cookies.get('admin') && currentSchedule">
        <h1> Events That Require Sign Up: </h1>
        <ul>
           <div v-for="(event, index) in currentSchedule.friday" :key="index">
               <div class = "eventPeople" v-if="event.signUp">
               <h3> {{ event.name }} </h3>
                    <ol>
                        <li v-for="(user, index) in event.usersSignedUp" :key = "index">
                            {{user.userName}} {{getLastName(user)}}
                        </li>
                    </ol>
               </div>
           </div>
        </ul>
        <ul>
           <div v-for="(event, index) in currentSchedule.saturday" :key="index">
              <div class = "eventPeople" v-if="event.signUp">
                  <h3> {{ event.name }} </h3>
                      <ol>
                          <li v-for="(user, index) in event.usersSignedUp" :key = "index">
                                {{user.userName}} {{getLastName(user)}}
                          </li>
                      </ol>
              </div>
           </div>
        </ul>
        <ul>
           <div v-for="(event, index) in currentSchedule.sunday" :key="index">
               <div class = "eventPeople" v-if="event.signUp">
                   <h3> {{ event.name }} </h3>
                       <ol>
                            <li v-for="(user, index) in event.usersSignedUp" :key = "index">
                                {{user.userName}} {{getLastName(user)}}
                            </li>
                       </ol>
               </div>
           </div>
        </ul>

    </div>
</div>
</template>

<script>
import { eventBus } from '../main.js';
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
        getLastName(user){
            return user.emailAddress.substring(user.emailAddress.indexOf(".")+1,user.emailAddress.indexOf("@"));
        },
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
                event.usersSignedUp=this.currentSchedule.friday[index].usersSignedUp;
            }
            if(date==='saturday')
            {
                event.usersSignedUp=this.currentSchedule.saturday[index].usersSignedUp;
            }
            if(date==='sunday')
            {
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
        if(this.$cookies.get('admin')=="true"){
           eventBus.$emit('adminSet', true);
        }
        if(this.$cookies.get('admin')=="false"){
           eventBus.$emit('adminSet', false);
        }
    }
}
</script>
<style>
@import '../../public/schedule.css';
</style>