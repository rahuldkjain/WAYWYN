import MakeApis from  './MakeApis.js'

export default {
    getContestLeaderBoard(callback,contestId){
        MakeApis.makeGetContestRequest("/api/leaderboard/static?contestId="+contestId,callback)
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
        MakeApis.makeGetOverallRequest("",callback)
    },
    getNumOfActiveContests(callback){
        MakeApis.makeGetOverallRequest("",callback)
    },
    getNumOfCorrectlyAnsweredQuestions(callback){
        MakeApis.makeGetOverallRequest("",callback)
    },
    getNumOfWronglyAnsweredQuestions(callback){
        MakeApis.makeGetOverallRequest("",callback)
    },
    getWinnersOfContest(callback, contestId){
        MakeApis.makeGetContestRequest(""+contestId,callback)
    }
}