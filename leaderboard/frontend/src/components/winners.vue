<template>
  <div>
 <b-container class="bv-example-row">
  <b-dropdown class="filters" text="Filters">
   <b-dropdown-item id="filter1" @click="staticFilter">Static</b-dropdown-item>
  <b-dropdown-item id="filter2" @click="dynamicFilter">Dynamic</b-dropdown-item>
  <b-dropdown-item id="filter3" @click="sportsFilter">Sports</b-dropdown-item>
  </b-dropdown>
  <br>
 <b-row>
​
   
  <b-col cols="3" v-for="(item,index) in filterContests" v-bind:key="index">
   <b-card title="Contest" style="text-align:center"  :header="item.contest_name" border-variant="success">
​
   <b-card-text>
  <b>Contest Name :</b> {{item.contestName}}
  {{item.score}}
  </b-card-text>
  <b-card-text>
   <b>Contest ID :</b> {{item.contestId}}
   {{item.score}}
  </b-card-text>
  <b-card-text>
  <b>Category :</b> {{item.category}}
  </b-card-text>
  <b-card-text>
  <b>Type :</b> {{item.type}}
  </b-card-text>
  <b-card-text>
  <b>End Date :</b> {{item.date}}
  </b-card-text>
​
  <b-button @click="contestLeaderBoard(item.contestId)"  variant="success" pill>LeaderBoard</b-button>
  <br>
  <b-button @click="winnersOfContest(item.contestId)" variant="primary" pill>See winners</b-button>
 </b-card><br></b-col>
  <!-- <b-col><b-card title="Card title" sub-title="Card subtitle">
  <b-card-text>
   Some quick example text to build on the <em>card title</em> and make up the bulk of the card's
   content.
  </b-card-text>
​
  <b-card-text>A second paragraph of text in the card.</b-card-text>
​
 </b-card></b-col>
  <b-col><b-card title="Card title" sub-title="Card subtitle">
  <b-card-text>
   Some quick example text to build on the <em>card title</em> and make up the bulk of the card's
   content.
  </b-card-text>
​
  <b-card-text>A second paragraph of text in the card.</b-card-text>
​
 </b-card></b-col>
 <b-col><b-card title="Card title" sub-title="Card subtitle">
  <b-card-text>
   Some quick example text to build on the <em>card title</em> and make up the bulk of the card's
   content.
  </b-card-text>
​
  <b-card-text>A second paragraph of text in the card.</b-card-text>
​
 </b-card></b-col> -->
​
 </b-row>
</b-container>
​
</div>
</template>
<script>
import ContestLeaderBoardVue from './ContestLeaderBoard.vue';
import {mapGetters, mapActions} from 'vuex';

export default {
  name : 'winners',
  data(){
   return{
    activeContests: [],
    filterContests: [],
    filtersSelected: false,
    filterValue: null
   }
  },
  created() {
   this.$store.dispatch('fetchActiveContests')
  },
  watch: {
   getActiveContests: function(newValue, oldValue){
     this.activeContests = newValue
     this.filterContests = newValue
     for(var index = 0; index < newValue.length; index++){
      if(this.activeContests[index].type == "dynamic"){
        this.activeContests[index].category = "mix"
      }
     }
   }
  },
  methods: {
    contestLeaderBoard(contestId){
     // this.$store.dispatch('fetchContestLeaderBoard',contestId);
     this.$router.push('/contestleaderboard/'+contestId)
    },
    winnersOfContest(contestId){
     //this.$store.dispatch('fetchWinnersOfContest',contestId);
     this.$router.push('/winnerboard/'+contestId)
    },
    staticFilter: function(){
     this.filtersSelected=true
     this.filterValue=document.getElementById("filter1").innerHTML.toLowerCase()
     this.matchesFilter();
    },
    dynamicFilter: function(){
     this.filtersSelected=true
     this.filterValue=document.getElementById("filter2").innerHTML.toLowerCase()
     this.matchesFilter()
    },
    sportsFilter: function(){
     this.filtersSelected=true
     this.filterValue=document.getElementById("filter3").innerHTML.toLowerCase()
     this.matchesFilter()
    },
    matchesFilter(){
     this.filterContests = []
     for(var i=0;i<this.activeContests.length;i++){
      if(this.filterValue==this.activeContests[i].type.toLowerCase()){
       this.filterContests.push(this.activeContests[i])
      }
      else if(this.filterValue==this.activeContests[i].category.toLowerCase()){
       this.filterContests.push(this.activeContests[i])
      }
     }
    }
  },

  computed: {
    ...mapGetters(['getWinnersOfContest','getNumOfActiveContests','getActiveContests'])
  }
}
</script>
<style>
.filters{
 padding-bottom: 80px;
}
</style>