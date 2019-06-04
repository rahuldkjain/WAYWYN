<template>
    <div>
        <h1 style="text-align:center;color:pink">Daily LeaderBoard</h1>
        <br>
        <b-row>
        <b-col v-for ="(ids,index) in dailyIdsArray.length" :key="index" style="text-align:center">
          <b-button variant="primary" @click="callDailyDashBoard(index)">Day {{index+1}}</b-button>
           <p>{{formatToDate(index)}}</p>


        </b-col>
        </b-row>
    </div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
    name : 'DailyIds',
    data(){
        return{
            dailyIdsArray: []
        }
    },
    created(){
        this.$store.dispatch('fetchDailyIds')
    },
    computed: {
        ...mapGetters(['getDailyIds'])
    },
    watch: {
      getDailyIds: function(newValue, oldValue){
          this.dailyIdsArray = newValue
      },
    },
    methods: {
        formatToDate(index){
            var date = new Date(this.dailyIdsArray[index] * 24 * 60 * 60 *1000);
            return date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear()
        },
        callDailyDashBoard(dayId){
            this.$store.dispatch('fetchDailyDashBoard',this.dailyIdsArray[dayId])
            this.$router.push('/dailydashboard/' + this.dailyIdsArray[dayId])
        }
    }
}
</script>
<style>

</style>
