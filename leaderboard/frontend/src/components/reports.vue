<template>

<div>
<b-container class="bv-example-row">
  <b-row>
    <b-col>
    <b-card title="Currently Active Contests">
      <b-card-text >
        <b-table striped hover :items="activeContests"></b-table>
      </b-card-text>
      <b-card-text>

      </b-card-text>
    </b-card>
</b-col>

  <b-col v-if="dynamicExist">
    <b-card title="User registered">
      <b-card-text >
        <b>Contest Name: </b>{{this.dynamicContestName}}
      </b-card-text>
      <b-card-text >
       <b>Contest ID: </b>{{this.dynamicId}}
        </b-card-text>
        <b-card-text >
       <b>Contest Date: </b>{{getNumOfActiveUsers.data[0].userEndDate}}
      </b-card-text>
      <b-card-text >
       <b>Number of active users: </b>{{getNumOfActiveUsers.data.length}}
      </b-card-text>
      <b-card-text>

      </b-card-text>
    </b-card>
  </b-col>
  </b-row>
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
          topQuestions: [],
          dynamicId: 0,
          dynamicExist: false,
          dynamicContestName: null
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
            contestName: newValue[index].contestName,
            type: newValue[index].type,
            category: newValue[index].category,
            contestEndDate: newValue[index].date
          }
          if(contest.type === "dynamic"){
            contest.category = "mix"
            this.dynamicExist =true
            this.dynamicId = contest.contestId
            this.dynamicContestName=contest.contestName
            this.$store.dispatch('fetchNumOfActiveUsers',this.dynamicId)
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
        ...mapGetters(['getActiveContests', 'getTopQuestions','getNumOfActiveUsers']),
        
    }
}
</script>
