<template>
<div>
    <b-table striped hover :items="winnerBoards"></b-table>
  </div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
    data(){
        return{
        winnerBoards: []
      }
    },
    created() {
        this.$store.dispatch('fetchWinnersOfContest', this.$route.params.contestId)
    },
    mounted() {
        this.$store.dispatch('fetchWinnersOfContest', this.$route.params.contestId)
    },
    watch: {
      getWinnersOfContest: function(newValue, oldValue){
          this.winnerBoards = newValue
      },
    },
    computed: {
        ...mapGetters(['getWinnersOfContest'])
    }
    
}
</script>
<style>

</style>
