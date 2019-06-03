<template>
    <div>
        
        <b-table striped hover :items="weeklyDashBoards" style="text-align:center"></b-table>
        
    </div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
    name: 'WeeklyDashBoard',
    data(){
        return{
            weeklyDashBoards: []
        }
    },
    computed: {
        ...mapGetters(['getWeeklyDashBoard'])
    },
    watch:{
    getWeeklyDashBoard: function(newValue, oldValue){
          this.weeklyDashBoards = newValue
      },
    }
}
</script>
<style>

</style>
