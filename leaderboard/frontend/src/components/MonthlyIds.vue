<template>
    <div>
         <h1 style="text-align:center;color:pink">Monthly LeaderBoard</h1>
         <b-row>
        <b-col v-for ="(ids,index) in monthlyIdsArray.length" :key="index" style="text-align:center">
          <b-button variant="primary" @click="callMonthlyDashBoard(index)" >Month {{index+1}}</b-button>
           <!-- <p>{{formatToDate(index)}}</p> -->

        </b-col>
        </b-row>
    </div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
    name : 'MonthlyIds',
    data(){
        return{
            monthlyIdsArray: []
        }
    },
    created(){
        this.$store.dispatch('fetchMonthlyIds')
    },
    computed: {
        ...mapGetters(['getMonthlyIds'])
    },
    watch: {
      getMonthlyIds: function(newValue, oldValue){
          this.monthlyIdsArray = newValue
      },
    },
    methods: {
        formatToDate(index){
            var utcSeconds = this.monthlyIdsArray[index];
            var date = new Date(0); // The 0 there is the key, which sets the date to the epoch
            date.setUTCDate(utcSeconds);
            return date.getUTCDate()+"/"+date.getUTCMonth()+"/"+date.getUTCFullYear()
        },
        callMonthlyDashBoard(monthId){
            this.$store.dispatch('fetchMonthlyDashBoard',this.monthlyIdsArray[monthId])
            this.$router.push('/monthlyDashboard/' + this.monthlyIdsArray[monthId])
        }
    }
}
</script>
<style>

</style>
