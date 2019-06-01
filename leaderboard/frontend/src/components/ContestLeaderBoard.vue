<template>
<div>
    <b-table striped hover :items="leaderBoards"></b-table>
  </div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
    data(){
        return{
        leaderBoards: []
      }
    },
    created() {
      this.$store.dispatch('fetchContestLeaderBoard',this.$route.params.contestId)
    },
    watch: {
      getContestLeaderBoard: function(newValue, oldValue){
          this.leaderBoards = newValue
      },
    },
    computed: {
        ...mapGetters(['getContestLeaderBoard'])
    }
    
}
</script>
<style>

</style>
