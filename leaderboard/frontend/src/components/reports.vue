<template>

<div>
<b-container class="bv-example-row">
  <b-row>
    <b-card title="Currently Active Contests">
      <b-card-text >
        <b-table striped hover :items="activeContests"></b-table>
      </b-card-text>
      <b-card-text>

      </b-card-text>
    </b-card>
  </b-row>
  <br>
  <br>
  
  <b-row>
      <b-card title="Most Correctly answered Questions">
      <b-card-text>
        <b-table striped hover :items="topQuestions"></b-table>
      </b-card-text>
    </b-card>
  </b-row>
</b-container>
</div>
</template>
<style>

</style>
<script>
import { mapGetters, mapActions } from 'vuex';
export default {
    name : 'reports',
    data() {
        return{
          registeredUsers: [],
          activeContests: [],
          topQuestions: []
        }
    },
    created(){
      this.$store.dispatch('fetchActiveContests')
      this.$store.dispatch('fetchTopQuestions')
    },
    mounted(){

    },
    watch: {

      getActiveContests: function(newValue, oldValue){
        for(var index=0; index < newValue.length; index++){
          let contest = {
            contestId: newValue[index].contestId,
            type: newValue[index].type,
            category: newValue[index].category,
            contestEndDate: newValue[index].date
          }
          if(contest.type === "dynamic"){
            contest.category = "mix"
          }
          this.activeContests.push(contest)
          console.log("In watch"+this.leaderBoards)
        }
      },
      getTopQuestions: function(newValue, oldValue){
            this.topQuestions = newValue
      }
    },
    computed: {
        ...mapGetters(['getActiveContests', 'getTopQuestions'])
    }
}
</script>
