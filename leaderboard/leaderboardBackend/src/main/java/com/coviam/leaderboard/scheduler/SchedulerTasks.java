package com.coviam.leaderboard.scheduler;

import com.coviam.leaderboard.entity.*;
import com.coviam.leaderboard.queryresult.UserAggregateScore;
import com.coviam.leaderboard.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class SchedulerTasks {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerTasks.class);
    @Autowired
    MonthlyLeaderboardRepository monthlyLeaderboardRepository;
    @Autowired
    ContestLeaderboardRepository contestLeaderboardRepository;
    @Autowired
    WeeklyLeaderboardRepository weeklyLeaderboardRepository;
    @Autowired
    DailyLeaderboardRepository  dailyLeaderboardRepository;
    @Autowired
    UserScoreRepository userScoreRepository;

    @Scheduled(fixedRate = 2000)
    public void updateContestLeaderboard(){
        // logger.info("Current Thread : {}", Thread.currentThread().getName());
        List<UserScore> userScoreList=userScoreRepository.findAllByOrderByScoreDesc();
        List<ContestLeaderboard> contestLeaderboardList=new ArrayList<ContestLeaderboard>();
        int rank =0;
        int previousScore=-1;
        for(UserScore user:userScoreList){
            ContestLeaderboard contestLeaderboard=new ContestLeaderboard();
            contestLeaderboard.setContestId(user.getContestId());
            contestLeaderboard.setScore(user.getScore());
            contestLeaderboard.setUserId(user.getUserId());
            contestLeaderboard.setUsername(user.getUsername());
            if(contestLeaderboard.getScore()!=previousScore){
                contestLeaderboard.setUserRank(++rank);
            }else {
                contestLeaderboard.setUserRank(rank);
            }
            previousScore=user.getScore();
            contestLeaderboardList.add(contestLeaderboard);
        }
        for(UserScore user:userScoreList){
            System.out.println(user.getUserId());
        }
        for(ContestLeaderboard contestLeaderboard:contestLeaderboardList){
            System.out.println(contestLeaderboard.getUsername());
        }
        contestLeaderboardRepository.save(contestLeaderboardList);
        return ;
    };

    @Scheduled(fixedRate = 4000)
    public void updateDailyLeaderboard(){
//        System.out.println("hi i am in update daily");
        java.util.Date today=new java.util.Date();
        Date dateVal=new Date(today.getTime());

        //find group by user id results an agg score
        List<Object> userScoreObjectList=userScoreRepository.findScoreSumGroupByUserId(dateVal);
        Iterator iterator=userScoreObjectList.iterator();
        //copy to agg score list
        List<UserAggregateScore> userScoreList=new ArrayList<UserAggregateScore>();
        while(iterator.hasNext()){
            Object[] object=(Object[]) iterator.next();
            UserAggregateScore aggregateScore=new UserAggregateScore();
            aggregateScore.setScore(Integer.parseInt(String.valueOf(object[2])));
            aggregateScore.setUser_id(Integer.parseInt(String.valueOf(object[0])));
            aggregateScore.setUsername(String.valueOf(object[1]));
            userScoreList.add(aggregateScore);
        }

        long epoch=System.currentTimeMillis()/1000/60/60/24;
        List<DailyLeaderboard> dailyLeaderboards=new ArrayList<DailyLeaderboard>();
        int rank=0;
        int previousScore=-1;
        //setting the daily leaderboard table
        for(UserAggregateScore userScore:userScoreList){
            DailyLeaderboard dailyLeaderboard=new DailyLeaderboard();
            dailyLeaderboard.setDayId((int)epoch);
            dailyLeaderboard.setUsername(userScore.getUsername());
            dailyLeaderboard.setScore(userScore.getScore());
            if(dailyLeaderboard.getScore()!=previousScore){
                dailyLeaderboard.setUserRank(++rank);
            }else{
                dailyLeaderboard.setUserRank(rank);
            }
            previousScore=dailyLeaderboard.getScore();
            dailyLeaderboards.add(dailyLeaderboard);
        }
        for(DailyLeaderboard dailyLeaderboard:dailyLeaderboards){
            System.out.println("daily: "+dailyLeaderboard.getUsername());
        }
        dailyLeaderboardRepository.save(dailyLeaderboards);
        return ;
    }

    @Scheduled(fixedRate = 6000)
    public void updateWeeklyLeaderboard(){
//        System.out.println("hi i am in update weekly");
        long today=System.currentTimeMillis()/1000/60/60/24;
        long weekId=System.currentTimeMillis()/1000/60/60/24/7;
        long startdate=weekId*7;
//        System.out.println(today+"   -------"+startdate);

        List<Object> dailyLeaderboardlist=dailyLeaderboardRepository.findByUserIdGroupByDateRange(startdate,today);
        Iterator iterator=dailyLeaderboardlist.iterator();
        List<WeeklyLeaderboard> weeklyLeaderboardList=new ArrayList<WeeklyLeaderboard>();

        int rank=0;
        int previousScore=-1;
        while (iterator.hasNext()){
            Object[] object=(Object[]) iterator.next();
            WeeklyLeaderboard weeklyLeaderboard=new WeeklyLeaderboard();
            weeklyLeaderboard.setUsername(String.valueOf(object[0]));
            weeklyLeaderboard.setScore(Integer.parseInt(String.valueOf(object[1])));
            weeklyLeaderboard.setWeekId((int)weekId);
            if(Integer.parseInt(String.valueOf(object[1]))!=previousScore){
                weeklyLeaderboard.setUserRank(++rank);
            }else{
                weeklyLeaderboard.setUserRank(rank);
            }
            previousScore=weeklyLeaderboard.getScore();
            weeklyLeaderboardList.add(weeklyLeaderboard);
        }
        weeklyLeaderboardRepository.save(weeklyLeaderboardList);
        return ;
    }

    @Scheduled(fixedRate = 8000)
    public void updateMonthlyLeaderboard(){
        System.out.println("hi i am in update monthly");
        long todayWeek=System.currentTimeMillis()/1000/60/60/24/7;
        long monthId=System.currentTimeMillis()/1000/60/60/24/7/4;
        long startWeek=monthId*4;
        System.out.println(todayWeek+"   -------"+startWeek);

        List<Object> weeklyLeaderboardlist=weeklyLeaderboardRepository.findByUserIdGroupByDateRange(startWeek,todayWeek);
        Iterator iterator=weeklyLeaderboardlist.iterator();
        List<MonthlyLeaderboard> monthlyLeaderboardList=new ArrayList<MonthlyLeaderboard>();

        int rank=0;
        int previousScore=-1;
        while (iterator.hasNext()){
            Object[] object=(Object[]) iterator.next();
            MonthlyLeaderboard monthlyLeaderboard=new MonthlyLeaderboard();
            monthlyLeaderboard.setUsername(String.valueOf(object[0]));
            monthlyLeaderboard.setScore(Integer.parseInt(String.valueOf(object[1])));
            monthlyLeaderboard.setMonthId((int)monthId);
            if(Integer.parseInt(String.valueOf(object[1]))!=previousScore){
                monthlyLeaderboard.setUserRank(++rank);
            }else{
                monthlyLeaderboard.setUserRank(rank);
            }
            previousScore=monthlyLeaderboard.getScore();
            monthlyLeaderboardList.add(monthlyLeaderboard);
        }

        monthlyLeaderboardRepository.save(monthlyLeaderboardList);
        return ;
    }

    @Scheduled(fixedRate = 20000)
    public void updateContestAndQuestionDetails(){





        return;
    }

}
