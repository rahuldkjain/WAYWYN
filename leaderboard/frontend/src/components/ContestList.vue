<template>
  <div>
    <b-table striped hover :items="leaderBoards" style="text-align:center"></b-table>
  </div>
</template>
<script>
  import { mapGetters } from 'vuex';
  export default {
    data(){
      return {
        leaderBoards: []
      }
    },
    created() {
        this.$store.dispatch('fetchActiveContests')
    },
    watch: {
        getActiveContests: function(newValue, oldValue){
          console.log("new value: " + newValue)

          for(var index=0; index < newValue.length; index++){
            let contest = {
              contestId: newValue[index].contestId,
              contestName: newValue[index].contestName,
              type: newValue[index].type,
              category: newValue[index].category,
              contestEndDate: newValue[index].date
            }
            if(contest.type === "dynamic"){
              contest.category = "mix"
            }
            this.leaderBoards.push(contest)
            console.log("In watch"+this.leaderBoards)
          }


        }
    },
    computed: {
      ...mapGetters(['getContestLeaderBoard', 'getActiveContests'])
    }
  }
</script>
<style>

</style>
