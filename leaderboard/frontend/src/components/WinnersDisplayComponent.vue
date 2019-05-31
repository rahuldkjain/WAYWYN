<template>
    <div>
    <b-table striped hover :items="contestWinnersList"></b-table>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
    name: 'WinnersDisplayComponent',
    data(){
        return{
            contestWinnersList: [
                {
                    username: "sfwrefg",
                    score: 20
                },
                {
                    username: "sfwrefg",
                    score: 20
                },
                {
                    username: "sfwrefg",
                    score: 20
                },
                {
                    username: "sfwrefg",
                    score: 20
                },
                {
                    username: "sfwrefg",
                    score: 20
                },
                {
                    username: "sfwrefg",
                    score: 20
                },
                {
                    username: "sfwrefg",
                    score: 20
                },
            ]
        }
    },
    computed: {
        ...mapGetters(['getWinnersOfContest'])
    }
}
</script>

<style>

</style>
