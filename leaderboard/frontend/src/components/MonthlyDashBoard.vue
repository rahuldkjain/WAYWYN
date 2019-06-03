<template>
    <div>
        <b-table striped hover :items="monthlyDashBoards"></b-table>
        
    </div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
    name: 'MonthlyDashBoard',
    data(){
        return{
            monthlyDashBoards: []
        }
    },
    computed: {
        ...mapGetters(['getMonthlyDashBoard'])
    },
    watch:{
    getMonthlyDashBoard: function(newValue, oldValue){
          this.monthlyDashBoards = newValue
      },
    }
}
</script>
<style>

</style>
