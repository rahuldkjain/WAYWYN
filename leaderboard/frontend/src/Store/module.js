import ToDoApis from '../Apis/ToDoApis.js'

export default {
    state: {
        contest_leader_board: [],
        daily_leader_board: [],
        weekly_leader_board: [],
        monthly_leader_board: [],
        numOfActiveUsers: 0,
        numOfActiveContests: 0,
        numOfCorrectlyAnsweredQuestions: 0,
        numOfWronglyAnsweredQuestions: 0,
        contestWinners: [],
        activeContests: [],
        topQuestions: [],
        dailyDashBoard: [],
        weeklyDashBoard:[],
        monthlyDashBoard: [],
        dailyIds: []
    },
    getters: {
        getContestLeaderBoard : (state) =>state.contest_leader_board,
        getDailyLeaderBoard : (state) =>state.daily_leader_board,
        getWeeklyLeaderBoard : (state) =>state.weekly_leader_board,
        getMonthlyLeaderBoard : (state) =>state.monthly_leader_board,
        getNumOfActiveUsers: (state)=> state.numOfActiveUsers,
        getNumOfActiveContests: (state)=> state.numOfActiveContests,
        getNumOfCorrectlyAnsweredQuestions: (state)=> state.numOfCorrectlyAnsweredQuestions,
        getNumOfWronglyAnsweredQuestions: (state)=> state.numOfWronglyAnsweredQuestions,
        getWinnersOfContest: (state)=> state.contestWinners,
        getActiveContests: (state) => state.activeContests,
        getTopQuestions: (state) => state.topQuestions,
        getDailyDashBoard: (state)=> state.dailyDashBoard,
        getWeeklyDashBoard: (state)=> state.weeklyDashBoard,
        getMonthlyDashBoard: (state)=> state.monthlyDashBoard,
        getDailyIds: (state) => state.dailyIds
    },
    mutations: {
        SET_CONTEST_LEADER_BOARD: (state,result)=>{
            state.contest_leader_board=result.data;

        },
        SET_DAILY_LEADER_BOARD: (state,result)=>{
            state.daily_leader_board=result.data;

        },
        SET_MONTHLY_LEADER_BOARD: (state,result)=>{
            state.monthly_leader_board=result.data;

        },
        SET_WEEKLY_LEADER_BOARD: (state,result)=>{
            state.weekly_leader_board=result.data;

        },
        SET_NUM_OF_ACTIVE_USERS: (state,result)=>{
            state.numOfActiveUsers=result.data;
        },
        SET_NUM_OF_ACTIVE_CONTESTS: (state,result)=>{
            state.numOfActiveContests=result.data
        },
        SET_NUM_OF_CORRECT_QUESTIONS: (state,result)=>{
            state.numOfCorrectlyAnsweredQuestions=result.data
        },
        SET_NUM_OF_WRONG_QUESTIONS: (state, result)=>{
            state.numOfWronglyAnsweredQuestions=result.data
        },
        SET_WINNERS_OF_CONTEST: (state, result)=>{
            state.contestWinners=result.data
        },
        SET_ACTIVE_CONTESTS: (state, result) => {
            state.activeContests = result.data
        },
        SET_TOP_QUESTIONS: (state, result) => {
            state.topQuestions = result.data
        },
        SET_DAILY_DASHBOARD: (state, result)=>{
            state.dailyDashBoard = result.data
        },
        SET_WEEKLY_DASHBOARD: (state, result)=>{
            state.weeklyDashBoard = result.data
        },
        SET_MONTHLY_DASHBOARD: (state, result)=>{
            state.monthlyDashBoard = result.data
        },
        SET_DAILY_IDS: (state, result)=>{
            state.dailyIds = result.data
        }


    },
    actions: {
        fetchContestLeaderBoard:(context,contestId) =>{
            ToDoApis.getContestLeaderBoard((result)=>{
                context.commit('SET_CONTEST_LEADER_BOARD',result.data)
            },contestId)
        },
        fetchDailyLeaderBoard:(context)=>{
            ToDoApis.getDailyLeaderBoard((result)=>{
                context.commit('SET_DAILY_LEADER_BOARD',result)
        })
        },
        fetchMonthlyLeaderBoard:(context)=>{
            ToDoApis.getMonthlyLeaderBoard((result)=>{
                context.commit('SET_MONTHLY_LEADER_BOARD',result)
        })
        },
        fetchWeeklyLeaderBoard:(context)=>{
            ToDoApis.getWeeklyLeaderBoard((result)=>{
                context.commit('SET_WEEKLY_LEADER_BOARD',result)
        })
        },
        fetchNumOfActiveUsers: (context, contestId) => {
            ToDoApis.getNumOfActiveUsers((result)=>{
                context.commit('SET_NUM_OF_ACTIVE_USERS',result)
            }, contestId)
        },
        fetchNumOfActiveContests: (context) => {
            ToDoApis.getNumOfActiveContests((result)=>{
                context.commit('SET_NUM_OF_ACTIVE_CONTESTS',result)
            })
        },
        fetchNumOfCorrectlyAnsweredQuestions: (context) => {
            ToDoApis.getNumOfCorrectlyAnsweredQuestions((result)=>{
                context.commit('SET_NUM_OF_CORRECT_QUESTIONS',result)
            })
        },
        fetchNumOfWronglyAnsweredQuestions: (context) =>{
            ToDoApis.getNumOfWronglyAnsweredQuestions((result)=>{
                context.commit('SET_NUM_OF_WRONG_QUESTIONS',result)
            })
        },
        fetchWinnersOfContest: (context,contestId)=>{
            ToDoApis.getWinnersOfContest((result)=>{
                context.commit('SET_WINNERS_OF_CONTEST',result.data)
            },contestId)
        },
        fetchActiveContests: (context)=>{
          ToDoApis.getActiveContestList((result)=>{
            context.commit('SET_ACTIVE_CONTESTS',result.data)
          })
        },
        fetchTopQuestions: (context) => {
          ToDoApis.getTopQuestionsList((result)=>{
            context.commit('SET_TOP_QUESTIONS', result.data)
          })
        },
        fetchDailyDashBoard: (context, dayId) => {
            ToDoApis.getDailyDashBoard((result)=>{
              context.commit('SET_DAILY_DASHBOARD', result.data)
            }, dayId)
          },
          fetchWeeklyDashBoard: (context) => {
            ToDoApis.getgetWeeklyDashBoard((result)=>{
              context.commit('SET_WEEKLY_DASHBOARD', result.data)
            })
          },
          fetchMonthlyDashBoard: (context) => {
            ToDoApis.getMonthlyDashBoard((result)=>{
              context.commit('SET_MONTHLY_DASHBOARD', result.data)
            })
          },
          fetchDailyIds: (context) => {
              ToDoApis.getDailyIds((result) =>{
                  context.commit('SET_DAILY_IDS', result.data)
              })
          },
          fetchWeeklyIds: (context) =>{
              ToDoApis.getWeeklyIds((result) =>{
                  context.commit('SET_WEEKLY_IDS', result.data)
              })
          },
          fetchMonthlyIds: (context) => {
              ToDoApis.getMonthlyIds((result) => {
                  context.commit('SET_MONTHLY_IDS', result.data)
              })
          }
  }
}
