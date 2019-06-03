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
        this.$store.dispatch('fetchDailyLeaderBoard')
        this.$store.dispatch('fetchWeeklyLeaderBoard')
        this.$store.dispatch('fetchMonthlyLeaderBoard')
        setInterval(()=>{
                this.$store.dispatch('fetchDailyLeaderBoard')
            },2000)
        setInterval(()=>{
            this.$store.dispatch('fetchWeeklyLeaderBoard')
        },2000)
        setInterval(()=>{
            this.$store.dispatch('fetchMonthlyLeaderBoard')
        },2000)
        //this.weeklyLeaderBoardFetch()

    },
    components: {
        LeaderboardCard
    },
    computed: {
        ...mapGetters(['getDailyLeaderBoard','getWeeklyLeaderBoard','getMonthlyLeaderBoard'])
    },
    watch: {
      getDailyLeaderBoard: function(newValue, oldValue){
        newValue.data.forEach(day => {
            if(this.dailyLeaderBoard.length < 10){
                this.dailyLeaderBoard.push(day)
            }
        });
      },
      getWeeklyLeaderBoard: function(newValue, oldValue){
        newValue.data.forEach(week => {
            if(this.weeklyLeaderBoard.length < 10){
                this.weeklyLeaderBoard.push(week)
            }
        });
        
      },
      getMonthlyLeaderBoard: function(newValue, oldValue){
        newValue.data.forEach(month => {
            if(this.monthlyLeaderBoard.length < 10){
                this.monthlyLeaderBoard.push(month)
            }
        });
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