<template>
    <div>
        <div v-for ="(ids,index) in dailyIdsArray.length" :key="index">
          <b-button @click="callDailyDashBoard(index)">Day {{index+1}}</b-button>
           <p>{{formatToDate(index)}}</p>


        </div>
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
            var utcSeconds = this.dailyIdsArray[index];
            var date = new Date(0); // The 0 there is the key, which sets the date to the epoch
            date.setUTCDate(utcSeconds);
            return date.getUTCDate()+"/"+date.getUTCMonth()+"/"+date.getUTCFullYear()
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
