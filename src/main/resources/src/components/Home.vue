<template>
<div>
    <div class = "body">
        <div class="title">
           <p class="text">Lake Forest Academy Weekend Activities</p>
           <g-signin-button
              :params="googleSignInParams"
              @success="onSignIn"
              v-if="signedIn">
               Signed in with LFA Email
              </g-signin-button>
           <g-signin-button
               :params="googleSignInParams"
               @success="onSignIn"
               v-else>
               Sign in with LFA Email Account
             </g-signin-button>

        </div>
        <div class = "quote">
              <p>"Newly improved weekend sign out system... Convenient and practical"</p>
        </div>


    </div>
    <div class="buttons">
        <a v-if="signedIn" href="/eventSignUp" style="text-decoration: none;"><div class="bigbutton"><p class="textInButton">Sign Up for Activity</p></div></a>
        <a v-else href="#" style="text-decoration: none;" ><div class="bigbutton"><p class="textInButton">Sign In to Continue</p></div></a>

        <router-link v-if="signedIn" :to="{ name: 'events', params: {currentUserName: userName } }"><div class="bigbutton"><p class="textInButton">Vote for Activities</p></div></router-link>
        <a v-else href="#" style="text-decoration: none;"><div class="bigbutton"><p class="textInButton">Sign In to Continue</p></div></a>
        <button class = "bigbutton" type="submit" onclick="window.open('WeekendSchedule.pdf')" style="text-decoration:none;" ><p class="textInButton1">View Weekend Schedule</p></button>
    </div>
    <div class="contacts"></div>
</div>

</template>
<!-- ADD SIGN OUT WHEN NOT LFA EMAIL -->
<script>
import Vue from 'vue'
import GSignInButton from 'vue-google-signin-button'
Vue.use(GSignInButton)
import UserDataService from "../services/UserDataService";

export default {
    data(){
        return {
            emailAddress:"",
            userName:"",
            signedIn:false,
            googleSignInParams: {
                    client_id: '978419002714-0ngcjc58363k85n3a6fpmrdl0tome13b.apps.googleusercontent.com'
            },
            user:""
        };
    },
    computed:{

    },
    components: {

    },
     methods:{
        onSignIn(user){
            const profile = user.getBasicProfile()
            this.emailAddress =profile.getEmail()
            console.log(this.emailAddress)
            if(this.emailAddress.indexOf("@students.lfanet.org")>-1){
                UserDataService.create(this.emailAddress)
                this.signedIn = true
                this.userName=this.emailAddress.substr(0, this.emailAddress.indexOf("."));


            }
            else{
                alert("Please sign in with an LFA Email Account")

                this.signedIn=false
            }

        }
      },
      mounted() {


      }
}


</script>
<style>
@import '../../public/stylesheet.css';

.g-signin-button {
  padding-top:20px;
  padding-bottom:20px;
  padding-left: 25px;
  padding-right: 25px;
  background-color: white;
  color: black;
  text-decoration: none;
  font-size: 20px;
  font-family: 'Clarkson',Helvetica,sans-serif;
  letter-spacing: 2.5px;
  font-weight: 300;
  height:70px;
  width:400px
}
.g-signin-button:hover {
    background-color:orange;
    color:white;
    cursor: pointer;
}
</style>