package com.coviam.leaderboard.scheduler;

import com.coviam.leaderboard.entity.Contest;
import com.coviam.leaderboard.entity.ContestLeaderboard;
import com.coviam.leaderboard.entity.DailyLeaderboard;
import com.coviam.leaderboard.entity.UserScore;
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
    @Autowired
    ContestRepository contestRepository;

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
        System.out.println("hi i am in update daily");
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

//        for(Object object:userScoreObjectList){
//            UserAggregateScore aggregateScore=new UserAggregateScore();
//            UserAggregateScore score1=(UserAggregateScore) object;
////            aggregateScore.setScore();
//            userScoreList.add(aggregateScore);
//        }

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

    @Scheduled(fixedRate = 2000)
    public List updateWeeklyLeaderboard(){
        return null;
    }

    @Scheduled(fixedRate = 2000)
    public List updateMonthlyLeaderboard(){
        return null;
    }

}
