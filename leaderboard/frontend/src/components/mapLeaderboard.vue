<template>
<div class='map-leaderboard' >
    <div class='map-leaderboard__weekly map-leaderboard__category'>
        <header class='map-leaderboard__weekly--header map-leaderboard__category-header'>
            <h1>Weekly</h1>
        </header>
        <!-- <div v-if="loading">Loading...</div> -->
        
        <LeaderboardCard v-if="" 
        class='map-leaderboard__weekly--card' 
        :rank="rank "
        :player="name"
        :score="score"
        style="color:gray"

         />
      




        <LeaderboardCard
        class='map-leaderboard__weekly--card' v-for="(record, index) in getWeeklyLeaderBoard.data" 
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
        class='map-leaderboard__all-time--card' v-for="(record, index) in getDailyLeaderBoard.data" 
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
        class='map-leaderboard__monthly--card' v-for="(record, index) in getMonthlyLeaderBoard.data" 
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
            rank: "rank",
            name: "name",
            score: "score"
        }
    },
    mounted(){
        this.$store.dispatch('fetchDailyLeaderBoard')
        this.$store.dispatch('fetchWeeklyLeaderBoard')
        this.$store.dispatch('fetchMonthlyLeaderBoard')

    },
    components: {
        LeaderboardCard
    },
    computed: {
        ...mapGetters(['getDailyLeaderBoard','getWeeklyLeaderBoard','getMonthlyLeaderBoard'])
    }
};
</script>
 
<style lang='scss' scoped>
    @import '../assets/_scss/_mapLeaderboard';
</style>