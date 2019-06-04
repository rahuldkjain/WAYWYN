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
          for(var index =0; index<newValue.length;index++){
              let leaderBoard = {
                  username: newValue[index].username,
                  score: newValue[index].score,
                  userRank: newValue[index].userRank
              }
              this.weeklyDashBoards.push(leaderBoard)
          }
      }
    }
}
</script>
<style>