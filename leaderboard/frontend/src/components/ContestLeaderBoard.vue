<template>
<div>
    <b-table striped hover :items="leaderBoards"></b-table>
  </div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
    data(){
        return {
            leaderBoards: [
                {
                    userId: 1,
                    contestId: 1,
                    username: "abc",
                    score: 20,
                    user_rank: 1
                },
                {
                    userId: 1,
                    contestId: 1,
                    username: "abc",
                    score: 20,
                    user_rank: 1
                },
                {
                    userId: 1,
                    contestId: 1,
                    username: "abc",
                    score: 20,
                    user_rank: 1
                },
                {
                    userId: 1,
                    contestId: 1,
                    username: "abc",
                    score: 20,
                    user_rank: 1
                },
                {
                    userId: 1,
                    contestId: 1,
                    username: "abc",
                    score: 20,
                    user_rank: 1
                }
                ,{
                    userId: 1,
                    contestId: 1,
                    username: "abc",
                    score: 20,
                    user_rank: 1
                },
                {
                    userId: 1,
                    contestId: 1,
                    username: "abc",
                    score: 20,
                    user_rank: 1
                }
            ]
        }
    },
    computed: {
        ...mapGetters(['getContestLeaderBoard'])
    }
}
</script>
<style>

</style>
