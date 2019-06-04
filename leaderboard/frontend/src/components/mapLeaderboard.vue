<template>
<div class='map-leaderboard' >
    <div class='map-leaderboard__weekly map-leaderboard__category'>
        <header class='map-leaderboard__weekly--header map-leaderboard__category-header'>
            <h1>Weekly</h1>
        </header>
        <!-- <div v-if="loading">Loading...</div> -->
      
        <LeaderboardCard 
        class='map-leaderboard__weekly--card' 
        :rank="rank "
        :player="name"
        :score="score"
        style="color:gray"

         />
       <div v-if="isWeeklyLeaderBoardNull" class="text-center">
       
                <!-- <b-button variant="primary" disabled>
            <b-spinner small></b-spinner>
            <span class="sr-only">Loading...</span>
        </b-button> -->

        <b-button variant="primary" disabled>
            <b-spinner small type="grow"></b-spinner>
            Loading...
        </b-button>
        </div>




        <LeaderboardCard
        class='map-leaderboard__weekly--card' v-for="(record, index) in weeklyLeaderBoard" 
        :key="'allTime' + index"
        :rank="record.userRank" 
        :player="record.username"
        :score="record.score"
        
         />

    </div>
    <div class='map-leaderboard__all-time map-leaderboard__category'>
        <header class='map-leaderboard__all-time--header map-leaderboard__category-header'>
            <h1>Daily</h1>
        </header>
        <!-- <div v-if="loading">Loading...</div> -->


        <LeaderboardCard
        class='map-leaderboard__weekly--card' 
        :rank="rank "
        :player="name"
        :score="score"
        style="color:gray"
         />
          <div v-if="isDailyLeaderBoardNull" class="text-center">
        
        <!-- <b-spinner variant="primary" label="Spinning"></b-spinner>
        <b-spinner variant="primary" type="grow" label="Spinning"></b-spinner> -->
        <b-button variant="warning" disabled>
            <b-spinner small type="grow"></b-spinner>
            Loading...
        </b-button>
      
        </div>
        <LeaderboardCard  
        class='map-leaderboard__all-time--card' v-for="(record, index) in dailyLeaderBoard" 
        :key="'allTime' + index" 
        :rank="record.userRank" 
        :player="record.username"
        :score="record.score"
         />

    </div>
    <div class='map-leaderboard__monthly map-leaderboard__category'>
        <header class='map-leaderboard__monthly--header map-leaderboard__category-header'>
            <h1>Monthly</h1>
        </header>
        <!-- <div v-if="loading">Loading...</div> -->


        <LeaderboardCard
        class='map-leaderboard__weekly--card' 
        :rank="rank "
        :player="name"
        :score="score"
        style="color:gray"
         />

          <div v-if="isMonthlyLeaderBoardNull" class="text-center">
        <!-- <b-spinner variant="danger" label="Spinning"></b-spinner>
        <b-spinner variant="danger" type="grow" label="Spinning"></b-spinner> -->
        <b-button variant="secondary" disabled>
            <b-spinner small type="grow"></b-spinner>
            Loading...
        </b-button>
        </div>

        <LeaderboardCard  
        class='map-leaderboard__monthly--card' v-for="(record, index) in monthlyLeaderBoard" 
        :key="'allTime' + index" 
        :rank="record.userRank" 
        :player="record.username"
        :score="record.score"
         />

    </div>
</div>
</template>
 
<script>
import LeaderboardCard from './leaderboardCard';
import {mapGetters, mapActions} from 'vuex';

export default {
    name: 'mapLeaderboard',
    data(){
        return {
            rank: "Rank",
            name: "Name",
            score: "Score",
            dailyLeaderBoard: [],
            weeklyLeaderBoard: [],
            monthlyLeaderBoard: []

        }
    },
    mounted(){
        setInterval(()=>{
                this.$store.dispatch('fetchDailyLeaderBoard')
            },1000)
        setInterval(()=>{
            this.$store.dispatch('fetchWeeklyLeaderBoard')
        },1000)
        setInterval(()=>{
            this.$store.dispatch('fetchMonthlyLeaderBoard')
        },1000)
        //this.weeklyLeaderBoardFetch()

    },
    components: {
        LeaderboardCard
    },
    computed: {
        ...mapGetters(['getDailyLeaderBoard','getWeeklyLeaderBoard','getMonthlyLeaderBoard']),
        isWeeklyLeaderBoardNull(){
            return this.weeklyLeaderBoard.length==0 ;
        },
        isDailyLeaderBoardNull(){
            return this.dailyLeaderBoard.length==0 ;
        },
        isMonthlyLeaderBoardNull(){
            return this.monthlyLeaderBoard.length==0;
        }
    },
    watch: {
      getDailyLeaderBoard: function(newValue, oldValue){
          newValue = newValue.data
          if(newValue.length > 10){
              newValue = newValue.slice(0,10)
          }
          this.dailyLeaderBoard = newValue
      },
      getWeeklyLeaderBoard: function(newValue, oldValue){
        newValue = newValue.data
          if(newValue.length > 10){
              newValue = newValue.slice(0,10)
          }
          this.weeklyLeaderBoard = newValue
        
      },
      getMonthlyLeaderBoard: function(newValue, oldValue){
        newValue = newValue.data
          if(newValue.length > 10){
              newValue = newValue.slice(0,10)
          }
        this.monthlyLeaderBoard = newValue
      }
      
    },
    methods: {
        weeklyLeaderBoardFetch(){
            for(var index=0;index<this.$store.getters.getWeekyLeaderBoard.data.length && index<10;index++){
        this.weeklyLeaderBoard[index]=this.$store.getters.getWeekyLeaderBoard.data[index]
        console.log(this.$store.getters.getWeekyLeaderBoard)
        }
    }
    }
};
</script>
 
<style lang='scss' scoped>
    @import '../assets/_scss/_mapLeaderboard';
</style>