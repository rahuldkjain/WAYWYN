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
    getNumOfActiveUsers(callback){
        MakeApis.makeGetOverallRequest("/api/leaderboard/activeusers?contestId=",callback)
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
    }
}
