<template>
    <div>
  <b-container class="bv-example-row">
    <br>
  <b-row>

    <b-col cols="3" v-for="(item,index) in winnersList" v-bind:key="index">
      <b-card title="Winners" style="text-align:center"   :header="item.contest_name"  border-variant="success">
    <b-card-text>
      <b>Name :</b> {{item.name}}
      {{item.contestId}}
    </b-card-text>
    <b-card-text>
    <b>Contest ID :</b> {{item.contestId}}
    </b-card-text>
    <b-button @click="contestLeaderBoard(item.contestId)"   variant="success" pill>LeaderBoard</b-button>
    <br>
    <b-button @click="winnersOfContest(item.contestId)" variant="primary" pill>See winners</b-button>
  </b-card><br></b-col>
    <!-- <b-col><b-card title="Card title" sub-title="Card subtitle">
    <b-card-text>
      Some quick example text to build on the <em>card title</em> and make up the bulk of the card's
      content.
    </b-card-text>

    <b-card-text>A second paragraph of text in the card.</b-card-text>

  </b-card></b-col>
    <b-col><b-card title="Card title" sub-title="Card subtitle">
    <b-card-text>
      Some quick example text to build on the <em>card title</em> and make up the bulk of the card's
      content.
    </b-card-text>

    <b-card-text>A second paragraph of text in the card.</b-card-text>

  </b-card></b-col>
  <b-col><b-card title="Card title" sub-title="Card subtitle">
    <b-card-text>
      Some quick example text to build on the <em>card title</em> and make up the bulk of the card's
      content.
    </b-card-text>

    <b-card-text>A second paragraph of text in the card.</b-card-text>

  </b-card></b-col> -->

  </b-row>
</b-container>
  
</div>
</template>
<script>
import ContestLeaderBoardVue from './ContestLeaderBoard.vue';
export default {
    name : 'winners',
    data(){
      return {
        winnersList: [
          {
            name: "abc",
            contestId: 1,
            contest_name: "Akshay"
          },
          {
            name: "abc",
            contestId: 1,
            contest_name: "Akshay"
          },
          {
            name: "abc",
            contestId: 1,
            contest_name: "Akshay"
          },
          {
            name: "abc",
            contestId: 1,
            contest_name: "Akshay"
          },
          {
            name: "abc",
            contestId: 1,
            contest_name: "Akshay"
          },
          {
            name: "abc",
            contestId: 1,
            contest_name: "Akshay"
          },
          {
            name: "abc",
            contestId: 1,
            contest_name: "Akshay"
          },
          {
            name: "abc",
            contestId: 1,
            contest_name: "Akshay"
          },
          {
            name: "abc",
            contestId: 1,
            contest_name: "Akshay"
          },
        ]
      }
    },
    methods: {
        contestLeaderBoard(contestId){
          this.$store.dispatch('fetchContestLeaderBoard',contestId);
          this.$router.push({name: 'ContestLeaderBoard'})
        },
        winnersOfContest(contesId){
          //this.$store.dispatch('fetchWinnersOfContest',contestId);
          this.$router.push({name: 'contestWinners'})
        }
    }
}
</script>
<style>

</style>
