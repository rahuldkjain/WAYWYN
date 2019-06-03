<template>
    <div>
        <h1 style="text-align:center;color:pink">Weekly LeaderBoard</h1>
        <br>
        <b-row>
        <b-col v-for ="(ids,index) in weeklyIdsArray.length" :key="index" style="text-align:center">
          <b-button variant="primary" @click="callWeeklyDashBoard(index)">Week {{index+1}}</b-button>
           <!-- <p>{{formatToDate(index)}}</p> -->

        </b-col>
        </b-row>
    </div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
    name : 'WeeklyIds',
    data(){
        return{
            weeklyIdsArray: []
        }
    },
    created(){
        this.$store.dispatch('fetchWeeklyIds')
    },
    computed: {
        ...mapGetters(['getWeeklyIds'])
    },
    watch: {
      getWeeklyIds: function(newValue, oldValue){
          this.weeklyIdsArray = newValue
      },
    },
    methods: {
        formatToDate(index){
            var utcSeconds = this.weeklyIdsArray[index];
            var date = new Date(0); // The 0 there is the key, which sets the date to the epoch
            date.setUTCDate(utcSeconds);
            return date.getUTCDate()+"/"+date.getUTCMonth()+"/"+date.getUTCFullYear()
        },
        callWeeklyDashBoard(weekId){
            this.$store.dispatch('fetchWeeklyDashBoard',this.weeklyIdsArray[weekId])
            this.$router.push('/weeklydashboard/' + this.weeklyIdsArray[weekId])
        }
    }
}
</script>
<style>

</style>
