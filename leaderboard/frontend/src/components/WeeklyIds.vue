<template>
    <div>
        <h1 style="text-align:center;color:pink">Weekly LeaderBoard</h1>
        <br>
        <b-row>
        <b-col v-for ="(ids,index) in weeklyIdsArray.length" :key="index" style="text-align:center">
          <b-button variant="primary" @click="callWeeklyDashBoard(index)">Week {{index+1}}</b-button>
           <p><b>Week Start Date: </b>{{formatToDate(index)}}</p>

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
            var week = new Date(this.weeklyIdsArray[index] * 7 * 24 * 60 * 60 * 1000);
            return week.getDate()+"/"+(week.getMonth() + 1) + "/" + week.getFullYear()
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
