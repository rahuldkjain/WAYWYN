<template>
    <div>
         <h1 style="text-align:center;color:pink">Monthly LeaderBoard</h1>
         <b-row>
        <b-col v-for ="(ids,index) in monthlyIdsArray.length" :key="index" style="text-align:center">
          <b-button variant="primary" @click="callMonthlyDashBoard(index)" >Month {{index+1}}</b-button>
           <p>{{formatToDate(index)}}</p>

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
            var month = new Date(this.monthlyIdsArray[index] * 30.44 * 24 * 60 * 60 * 1000);
            return (month.getMonth() + 1) + "/" + month.getFullYear()
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
