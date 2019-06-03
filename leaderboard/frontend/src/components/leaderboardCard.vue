<template>
<div class='leaderboard-card' >
    <div class='leaderboard-card__info'>
        <span class='leaderboard-card__place'>{{ rank }}</span>
        <router-link class='leaderboard-card__player' :to="'/leaderboard/user/' + player.steamid64">
      
            <div class='leaderboard-card__name'>
               {{ player }}
            </div>
        </router-link>
    </div>
    <span class='leaderboard-card__time'>{{ formatScore(score) }}</span>
</div>
</template>
 
<script>
import PlayerImage from './playerImage';
export default {
    name: 'leaderboardCard',
    props: ['player', 'rank', 'score'],
    methods: {
        formatScore(score) {
            if(this.isTime) {
                return score.toFixed(2) + 's'
            }
            return score;
        }
    },
    components: {
        PlayerImage
    }
};
</script>
 
<style lang='scss' scoped>
    @import '../assets/_scss/_leaderboardCard';
</style>