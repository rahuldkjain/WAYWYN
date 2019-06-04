<template>
    <div>
        <b-table striped hover :items="dailyDashBoards" style="text-align:center"></b-table>
        
    </div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
    name: 'DailyDashBoard',
    data(){
        return{
            dailyDashBoards: []
        }
    },
    computed: {
        ...mapGetters(['getDailyDashBoard'])
    },
    watch:{
    getDailyDashBoard: function(newValue, oldValue){
          for(var index=0;index<newValue.length;index++)
            {
                let daily = {
                    username: newValue[index].username,
                    score: newValue[index].score,
                    userRank: newValue[index].userRank
                }
                this.dailyDashBoards.push(daily)
            }
      },
    }
}
</script>
<style>

</style>
