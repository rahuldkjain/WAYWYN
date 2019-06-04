<template>
    <div>
        <b-table striped hover :items="monthlyDashBoards" style="text-align:center"></b-table>
        
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
          for(var index=0;index<newValue.length;index++){
              let leaderBoard = {
                  username: newValue[index].username,
                  score: newValue[index].score,
                  userRank: newValue[index].userRank
              }
              this.monthlyDashBoards.push(leaderBoard)
          }
      },
    }
}
</script>
<style>

</style>