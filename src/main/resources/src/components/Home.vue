<template>
<div>
    <div class = "body">
        <div class="title">
           <p class="text">Lake Forest Academy Weekend Activities</p>
           <div id = "signin">
                <div  class="g-signin2"></div>
           </div>

        </div>
        <div class = "quote">
              <p>"Newly improved weekend sign out system... Convenient and practical"</p>
        </div>


    </div>
    <div class="buttons">
        <a href="/eventSignUp" style="text-decoration: none;"><div class="bigbutton"><p class="textInButton">Sign Up for Activity</p></div></a>
        <a href="/events" style="text-decoration: none;"><div class="bigbutton"><p class="textInButton">Vote for Activities</p></div></a>
        <button class = "bigbutton" type="submit" style="text-decoration:none;" ><p class="textInButton1">View Weekend Schedule</p></button>
    </div>

</div>

</template>
<script>
import UserDataService from "../services/UserDataService";
export default {
    data(){
        return {
            emailAddress:"",
            signedIn:false
        };
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


            }
            else{
                alert("Please sign in with an LFA Email Account")
                var auth2 = gapi.auth2.getAuthInstance();
                auth2.signOut().then(function () {
                  console.log('User signed out.');
                });
                this.signedIn=false
            }

        }
      },
      mounted() {
          gapi.load('auth2', function() {
              gapi.auth2.init({
                 client_id: '978419002714-0ngcjc58363k85n3a6fpmrdl0tome13b.apps.googleusercontent.com'
              });
            });

          gapi.signin2.render('signin', {
               'width': 240,
               'height': 50,
               'longtitle': true,
               'onsuccess': this.onSuccess,
          })

      }
}


</script>
<style>
@import '../../public/stylesheet.css';

</style>