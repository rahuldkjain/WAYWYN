package com.coviam.leaderboard.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

@Component
public class SchedulerTasks {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerTasks.class);
    @Scheduled(fixedRate = 2000)
    public List updateContestLeaderboard(){
        // logger.info("Current Thread : {}", Thread.currentThread().getName());
        return null;
    };

    @Scheduled(fixedRate = 2000)
    public List updateDailyLeaderboard(){

        return null;
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
