<template>
<div class="col-md-6">
    <b-button href="/createSchedule">Create Schedule</b-button>
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
    <div class="col-md-6">
          <div v-if="currentSchedule">

            <a class="badge badge-warning"
              :href="'/schedules/' + currentSchedule.id"
            >
              View Schedule
            </a>
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
            schedules:[]
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
               })


               .catch(e => {
                 console.log(e);
               });
        },
        setActiveSchedule(schedule, index) {
          this.currentSchedule = schedule;
          this.currentIndex = index;

        },
    },
    mounted(){
        this.retrieveSchedules()
<<<<<<< HEAD
        if(this.$cookies.get('admin')=='false')
        {
           alert("Sign in as an admin to access this page...")
           this.$router.push('home')
        }
    }
}
</script>
=======

>>>>>>> fb1ba08a534df07f0e6aeda92b72245f1f8eff31
