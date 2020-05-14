<template>
    <div class = "containerHome">

        <div v-if="!$isMobile()">

            <div class="title">
                   <p class="text">Lake Forest Academy Weekend Activities</p>
                   <g-signin-button
                      :params="googleSignInParams"
                      @success="onSignIn"
                      v-if="signedIn"
                      class="computer-signin">
                       Signed in with LFA Email
                      </g-signin-button>
                   <g-signin-button
                       :params="googleSignInParams"
                       @success="onSignIn"
                       v-else
                       class="computer-signin">
                       Sign in with LFA Email Account
                     </g-signin-button>


                </div>
                <p class = "desc">Convenient and improved sign out system.</p>
                <div class = "column" v-if="signedIn">
                    <a href="/add" class="bigbutton">Request Activities</a>
                    <a href="/events" class="bigbutton">Vote for Activities</a>
                    <a :href="'/schedules/'+ latestId" class="bigbutton">View Weekend Schedule</a>

                </div>
                <a href="/schedules" class="bigbutton" v-if="admin">View All Schedules</a>


        </div>

        <div v-else>



            <div class="mobile-title">

                <p class="mobile-text">Lake Forest Academy Weekend Activities</p>

                <g-signin-button
                    :params="googleSignInParams"
                    @success="onSignIn"
                    class="mobile-signin"
                v-if="signedIn" >
                    Signed in with LFA Email
                </g-signin-button>

                <g-signin-button
                    :params="googleSignInParams"
                    @success="onSignIn"
                    class="mobile-signin"
                v-else>
                    Sign in with LFA Email Account
                </g-signin-button>

                <div class = "mobile-column" v-if="signedIn">
                     <a href="/add" class="mobile-bigbutton">Request Activities</a>
                     <a href="/events" class="mobile-bigbutton">Vote for Activities</a>
                     <a :href="'/schedules/'+ latestId" class="mobile-bigbutton">View Weekend Schedule</a>
                    <a href="/schedules" class="mobile-bigbutton" v-if="admin">View All Schedules</a>
                </div>

            </div>




        </div>


    </div>



</template>
<!-- ADD SIGN OUT WHEN NOT LFA EMAIL -->
<script>
import { eventBus } from '../main.js';
import Vue from 'vue'
import GSignInButton from 'vue-google-signin-button'
Vue.use(GSignInButton)
import UserDataService from "../services/UserDataService";
import VueCookies from 'vue-cookies'
Vue.use(VueCookies)
import ScheduleDataService from "../services/ScheduleDataService";
export default {
    data(){
        return {
            emailAddress:"",
            userName:"",
            signedIn:false,
            googleSignInParams: {
                    client_id: '978419002714-0ngcjc58363k85n3a6fpmrdl0tome13b.apps.googleusercontent.com'
            },
            user:"",
            currentUser:null,
            hello:"hello",
            admin:false,
            latestId:0,
            schedules:[]
        };
    },
    computed:{
    },
    components: {
    },
     methods:{
        retrieveSchedules(){
            ScheduleDataService.getAll()
              .then(response => {
                 this.schedules = response.data;
                 console.log(response.data);
                 this.findLatestId()
               })
              .catch(e => {
                 console.log(e);
              });
        },
        onSignIn(user){
            const profile = user.getBasicProfile()
            this.emailAddress =profile.getEmail()
            console.log(this.emailAddress)
            if(this.emailAddress.indexOf("@students.lfanet.org")>-1){
                UserDataService.create(this.emailAddress)
                this.signedIn = true
                this.userName=this.emailAddress.substr(0, this.emailAddress.indexOf("."));
                this.admin=false
                UserDataService.getCurrentUser(this.userName)
                    .then(response => {
                        this.currentUser = response.data[0];
                        console.log(this.currentUser);
                        this.setUserCookie()
                        this.$cookies.set('admin', false)
                        eventBus.$emit('adminSet', false);
                    })
                    .catch(e => {
                         console.log(e);
                    });
            }
            else if(this.emailAddress.indexOf("@gmail.com")>-1){
                UserDataService.create(this.emailAddress)
                this.signedIn = true
                this.userName=this.emailAddress.substr(0, this.emailAddress.indexOf("."));
                this.admin = true
                UserDataService.getCurrentUser(this.userName)
                    .then(response => {
                       this.currentUser = response.data[0];
                       console.log(this.currentUser);
                       this.setUserCookie()
                       this.$cookies.set('admin', true)
                       eventBus.$emit('adminSet', true);
                    })
                    .catch(e => {
                       console.log(e);
                     });
            }
            else{
                alert("Please sign in with an LFA Email Account")
                //UserDataService.create(this.emailAddress)
                //this.signedIn = true
                //this.userName=this.emailAddress.substr(0, this.emailAddress.indexOf("."));
                this.signedIn=false
            }
        },
        findLatestId(){
                this.latestId=this.schedules[0].id
                for(var i = 0;i<this.schedules.length;i++)
                {
                    if(this.schedules[i].id>this.latestId)
                    {
                        this.latestId=this.schedules[i].id
                    }
                }
                this.$cookies.set('latestId', this.latestId)
        },
        setUserCookie(){
            this.$cookies.set('user',this.currentUser);
        }
      },
      mounted() {
        if(this.$cookies.get('user')!=null)
        {
            this.signedIn=true
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(this.$cookies.get('admin')=="true"){
            this.admin=true;
            eventBus.$emit('adminSet', true);
        }
        if(this.$cookies.get('admin')=="false"){
            this.admin=false;
            eventBus.$emit('adminSet', false);
         }
        this.retrieveSchedules()
      }
}
</script>
<style>
@import '../../public/stylesheet.css';
.computer-signin {
  text-align:center;
  display: table-cell; vertical-align: middle;
  background-color: white;
  color: black;
  text-decoration: none;
  font-size: 1.15vw;
  font-family: 'Clarkson',Helvetica,sans-serif;
  letter-spacing: 2.5px;
  height:4vw;
  width:22vw;
}
.mobile-signin {
  text-align:center;
  display: table-cell; vertical-align: middle;
  background-color: white;
  color: black;
  text-decoration: none;
  font-size: 15px;
  font-family: 'Clarkson',Helvetica,sans-serif;
  letter-spacing: 2.5px;
  height:12vw;
  width:80vw;
  margin-left:15%;
}
.computer-signin:hover {
    background-color:orange;
    color:white;
    cursor: pointer;
}
</style>