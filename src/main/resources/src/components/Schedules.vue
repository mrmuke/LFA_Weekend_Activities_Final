<template>
<div class = "body">
    <div class = "list row">
        <div class="col-md-8">
            <h1> Welcome, Admin </h1>
            <div class="input-group mb-3">
               <input type="text" class="form-control" placeholder="Search by date"
                v-model="date"/>
            </div>
            <h4>All Schedules List</h4>
            <ul class="list-group">
                  <li class="list-group-item"
                  :class="{ active: index == currentIndex }"
                  v-for="(schedule, index) in filteredSchedules"
                  :key="index"
                  @click="setActiveSchedule(schedule, index)"
                  >
                   {{ schedule.date }}
                </li>
            </ul>
        </div>
        <div class="col-md-6" v-if="currentSchedule">
           <b-button
            :href="'/schedules/' + currentSchedule.id"
           >
           View Schedule
           </b-button>
           <b-button @click = "deleteSchedule">Delete Schedule</b-button>
        </div>
        <div class = "col-md-8">
            <div class="button_cont" style="margin-top:3%"><a class="create" href="/createSchedule" rel="nofollow noopener">Create Schedule</a></div>
        </div>
    </div>
</div>
</template>
<script>
import ScheduleDataService from '../services/ScheduleDataService';
export default{
    data(){
        return{
            currentIndex: -1,
            date: "",
            index:-1,
            currentSchedule:null,
            schedules:[],
            latestId:0
        }
    },
    computed:{
        filteredSchedules(){
             return this.schedules.filter(schedule => {
                return schedule.date.toLowerCase().includes(this.date.toLowerCase())
             })
        }
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
        getSchedulesAfterDelete(){
            ScheduleDataService.getAll()
              .then(response => {
                 this.schedules = response.data;
                 console.log(response.data);
                 this.findLatestId()
                 location.reload()

              })


              .catch(e => {
                 console.log(e);
               });
        },
        deleteSchedule(){
            if(confirm("Are you sure you want to delete this schedule?"))
            {
                ScheduleDataService.delete(this.currentSchedule.id)
                        .then(response => {
                          console.log(response.data);
                          this.getSchedulesAfterDelete()

                        })
                        .catch(e => {
                          console.log(e);
                        });
            }
        },
        findLatestId(){
             this.latestId=this.schedules[0].id
             console.log(this.latestId)
             for(var i = 0;i<this.schedules.length;i++)
             {
                 if(this.schedules[i].id>this.latestId)
                 {
                     this.latestId=this.schedules[i].id
                 }
             }
             console.log(this.latestId)
             this.$cookies.set('latestId', this.latestId)
        },
        setActiveSchedule(schedule, index) {
          this.currentSchedule = schedule;
          this.currentIndex = index;

        },

    },
    mounted(){
        this.retrieveSchedules()
        if(this.$cookies.get('admin')=='false')
        {
           alert("Sign in as an admin to access this page...")
           this.$router.push('home')
        }
    }
}
</script>
<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
.body{
    background-image: url(../../public/img/new-background.jpg);
    font-family: Segoe UI,Frutiger,Frutiger Linotype,Dejavu Sans,Helvetica Neue,Arial,sans-serif;
    background-size: cover;
    overflow:auto;
    background-repeat: no-repeat;
    height:100%;
    width:100%;
    background-position: 50% 50%;
    position:absolute;
}
</style>