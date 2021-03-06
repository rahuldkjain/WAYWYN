import MakeApis from  './MakeApis.js'

export default {
    // getContestLeaderBoard(callback,contestId){
    //     MakeApis.makeGetContestRequest("/api/leaderboard/static?contestId="+contestId,callback)
    // },
    getContestLeaderBoard(callback,contestId){
        MakeApis.makeGetContestRequest("/api/leaderboard/contest?contestId="+contestId,callback)
    },
    getDailyLeaderBoard(callback){
        MakeApis.makeGetOverallRequest("/api/leaderboard/daily",callback)
    },
    getMonthlyLeaderBoard(callback){
        MakeApis.makeGetOverallRequest("/api/leaderboard/monthly",callback)
    },
    getWeeklyLeaderBoard(callback){
        MakeApis.makeGetOverallRequest("/api/leaderboard/weekly",callback)
    },
    getNumOfActiveUsers(callback, contestId){
        MakeApis.makeGetOverallRequest("/api/leaderboard/reports/activeusers?contestId="+contestId,callback)
    },
    getNumOfActiveContests(callback){
        MakeApis.makeGetOverallRequest("/api/leaderboard/reports/activecontests",callback)
    },
    // getNumOfCorrectlyAnsweredQuestions(callback){
    //     MakeApis.makeGetOverallRequest("",callback)
    // },
    getWinnersOfContest(callback, contestId){
        MakeApis.makeGetContestRequest("/api/leaderboard/reports/winners?contestId="+contestId,callback)
    },
    getActiveContestList(callback){
      MakeApis.makeGetContestRequest("/api/leaderboard/reports/activecontests", callback)
    },
    getTopQuestionsList(callback){
      MakeApis.makeGetContestRequest("/api/leaderboard/reports/topquestion", callback)
    },
    getDailyIds(callback){
        MakeApis.makeGetOverallRequest("/api/leaderboard/dayids", callback)
    },
    getDailyDashBoard(callback,dayId){
        MakeApis.makeGetOverallRequest("/api/leaderboard/dailylb?dayId="+dayId,callback)
    },

    getWeeklyIds(callback){
        MakeApis.makeGetOverallRequest("/api/leaderboard/weekids", callback)
    },
    getWeeklyDashBoard(callback,weekId){
        MakeApis.makeGetOverallRequest("/api/leaderboard/weeklylb?weekId="+weekId,callback)
    },

    getMonthlyIds(callback){
        MakeApis.makeGetOverallRequest("/api/leaderboard/monthids", callback)
    },
    getMonthlyDashBoard(callback,monthId){
        MakeApis.makeGetOverallRequest("/api/leaderboard/monthlylb?monthId="+monthId,callback)
    },
    getAllCategories(callback){
        MakeApis.makeGetOverallRequest("/category/contest/getallcategory", callback)
        
    }
}
