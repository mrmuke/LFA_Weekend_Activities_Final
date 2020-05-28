<template>

<div class = "schedule-body">
    <div class = "header">
        <div class = "container">
            <h1 v-if="currentSchedule"><u>{{this.currentSchedule.date}}</u></h1>
        </div>
    </div>
    <div class = "container">

    <div v-if="currentSchedule">
        <h2>Friday: </h2>
        <ul>
          <li v-for="(event, index) in currentSchedule.friday" :key="index">
              {{ event.timeSlot }} {{event.name}} <p v-if="!event.signUp"></p><button class="default" v-else-if="notSignedUp(event)" @click = "signUpUser(event, 'friday', index)">Sign Up ({{spotsLeft(event)}})</button> <button class="default" v-else @click="removeFromList(event)">Signed Up (No.{{place(event)}} on the List)</button>
          </li>
        </ul>
    </div>
    <div v-if="currentSchedule">
       <h2>Saturday: </h2>
       <ul>
            <li v-for="(event, index) in currentSchedule.saturday" :key="index">
               {{ event.timeSlot }} {{event.name}} <p v-if="!event.signUp"></p><button class="default" v-else-if="notSignedUp(event)" @click="signUpUser(event, 'saturday', index)">Sign Up ({{spotsLeft(event)}})</button> <button class="default" v-else @click="removeFromList(event)">Signed Up (No.{{place(event)}} on the List)</button>
            </li>
       </ul>
    </div>
    <div v-if="currentSchedule">
       <h2>Sunday: </h2>
        <ul>
             <li v-for="(event, index) in currentSchedule.sunday" :key="index">
                 {{ event.timeSlot }} {{event.name}} <p v-if="!event.signUp"></p> <button class="default" v-else-if="notSignedUp(event)" @click="signUpUser(event, 'sunday', index)">Sign Up ({{spotsLeft(event)}})</button> <button class="default" v-else @click="removeFromList(event)">Signed Up (No.{{place(event)}} on the List)</button>
             </li>
        </ul>
    </div>
    <div v-if="currentSchedule">
        <h1> Events That Require Sign Up: </h1>
        <ul>
           <div v-for="(event, index) in currentSchedule.friday" :key="index">
               <div class = "eventPeople" v-if="event.signUp">
               <h3><u> {{ event.name }} </u></h3>
                <h4> Max Number of People: {{event.personLimit}}</h4>
                    <ol>
                        <li v-for="(user, index) in event.usersSignedUp" :key = "index">
                            {{user.userName}} {{getLastName(user)}} <p v-if="waitlist(event, user)"> - WaitList</p>
                        </li>
                    </ol>
                    <button class="default" v-if="$cookies.get('user').admin" @click= "sendEmail(event)">Send Notification</button>
               </div>
           </div>
        </ul>
        <ul>
           <div v-for="(event, index) in currentSchedule.saturday" :key="index">
              <div class = "eventPeople" v-if="event.signUp">
                  <h3><u> {{ event.name }}  </u></h3>
                  <h4> Max Number of People: {{event.personLimit}}</h4>
                      <ol>
                          <li v-for="(user, index) in event.usersSignedUp" :key = "index">
                                {{user.userName}} {{getLastName(user)}}<p v-if="waitlist(event, user)"> - WaitList</p>
                          </li>
                      </ol>
                      <button class="default" v-if="$cookies.get('user').admin" @click= "sendEmail(event)">Send Notification</button>
              </div>
           </div>
        </ul>
        <ul>
           <div v-for="(event, index) in currentSchedule.sunday" :key="index">
               <div class = "eventPeople" v-if="event.signUp">
                   <h3><u> {{ event.name }} </u></h3>
                   <h4> Max Number of People: {{event.personLimit}}</h4>
                       <ol>
                            <li v-for="(user, index) in event.usersSignedUp" :key = "index">
                                {{user.userName}} {{getLastName(user)}}<p v-if="waitlist(event, user)"> - WaitList</p>
                            </li>
                       </ol>
                       <button class="default" v-if="$cookies.get('user').admin" @click= "sendEmail(event)">Send Notification</button>
               </div>
           </div>
        </ul>

    </div>
    </div>
</div>
</template>

<script>
import EmailDataService from "../services/EmailDataService"
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
        waitlist(event, user){
            if((event.usersSignedUp.indexOf(user)+1)>event.personLimit)
            {
                return true;
            }
            return false
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
        sendEmail(event){
            for(var i =0;i<event.usersSignedUp.length;i++){
                EmailDataService.sendEmail(event.usersSignedUp[i].emailAddress, event.name, event.timeSlot)
                console.log(event.timeSlot)
            }
            alert("Emails Successfully Sent!")


        },
        spotsLeft(event){

            if(event.usersSignedUp.length>=event.personLimit)
            {
                return "Waitlist";
            }
            return "" + (event.personLimit-event.usersSignedUp.length) + " spots left";
        },
        removeFromList(event)
        {
            if (window.confirm('Are you sure you want to be removed from the sign up list?'))
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
            }
            else
            {
                return;
            }


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
    }

}
</script>
<style>
@import '../../public/schedule.css';
@import '../../public/stylingvue.css';
.container{
    margin:auto;
    width:1020px
}
.default{
    margin-top:2px;
}
</style>