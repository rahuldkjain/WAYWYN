import Vue from 'vue';
import Router from 'vue-router';
import MapLeaderboard from '@/components/mapLeaderboard';
import PlayerLeaderboard from '@/components/playerLeaderboard';
import SeasonalLeaderboard from '@/components/seasonalLeaderboard';
import reports from '@/components/reports';
import winners from '@/components/winners';
import ContestLeaderBoard from '@/components/ContestLeaderBoard' ;
import WinnersDisplayComponent from '@/components/WinnersDisplayComponent';
import ContestList from "@/components/ContestList"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: MapLeaderboard,
    },
    {
      path: '/contestWinners',
      name: 'contestWinners',
      component: WinnersDisplayComponent
    },
    {
      path: '/leaderboard/map',
      name: 'Map Leaderboard',
      component: MapLeaderboard,
    },

    {
      path: '/report',
      name: 'reports',
      component: reports,
    },

    {
      path: '/winner',
      name: 'winners',
      component: winners,
    },
    {
      path: '/contests',
      name: 'contests',
      component: ContestList,
    },
    {
      path: '/contestleaderbopard',
      name: 'ContestLeaderBoard',
      component: ContestLeaderBoard,
    },
    // {
    //   path: '/leaderboard/map/:mapid',
    //   name: 'Map Leaderboard id',
    //   component: MapLeaderboard,
    // },
    // {
    //   path: '/leaderboard/user',
    //   name: 'Player Leaderboard',
    //   component: PlayerLeaderboard,
    // },
    {
      path: '/leaderboard/user/:userid',
      name: 'Player Leaderboard',
      component: PlayerLeaderboard,
    },
    //{
    //   path: '/leaderboard/user/map/:mapid',
    //   name: 'Player Map Leaderboard',
    //   component: PlayerLeaderboard,
    // },
    {
      path: '/leaderboard/seasonal',
      name: 'Seasonal Leaderboard',
      component: SeasonalLeaderboard,
    },
    // {
    //   path: '/leaderboard/seasonal/map/:mapid',
    //   name: 'Seasonal Map Leaderboard',
    //   component: SeasonalLeaderboard,
    // }
  ]
})
