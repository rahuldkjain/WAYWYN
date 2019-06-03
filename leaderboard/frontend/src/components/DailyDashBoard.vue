<template>
    <div>
        <b-table striped hover :items="dailyDashBoards"></b-table>
        
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
          this.dailyDashBoards = newValue
      },
    }
}
</script>
<style>

</style>
