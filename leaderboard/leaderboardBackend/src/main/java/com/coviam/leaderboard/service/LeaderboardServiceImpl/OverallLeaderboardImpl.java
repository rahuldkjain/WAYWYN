package com.coviam.leaderboard.service.LeaderboardServiceImpl;

import com.coviam.leaderboard.entity.ContestLeaderboard;
import com.coviam.leaderboard.entity.DailyLeaderboard;
import com.coviam.leaderboard.entity.MonthlyLeaderboard;
import com.coviam.leaderboard.entity.WeeklyLeaderboard;
import com.coviam.leaderboard.repository.ContestLeaderboardRepository;
import com.coviam.leaderboard.repository.DailyLeaderboardRepository;
import com.coviam.leaderboard.repository.MonthlyLeaderboardRepository;
import com.coviam.leaderboard.repository.WeeklyLeaderboardRepository;
import com.coviam.leaderboard.service.OverallLeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverallLeaderboardImpl implements OverallLeaderboardService {

    @Autowired
    DailyLeaderboardRepository dailyLeaderboardRepository;

    @Autowired
    WeeklyLeaderboardRepository weeklyLeaderboardRepository;

    @Autowired
    MonthlyLeaderboardRepository monthlyLeaderboardRepository;

    @Autowired
    ContestLeaderboardRepository contestLeaderboardRepository;

    @Override
    public List<DailyLeaderboard> getDailyLeaderboard() {
        long dayId = System.currentTimeMillis()/1000/60/60/24;
        List<DailyLeaderboard> dailyLeaderboardList= dailyLeaderboardRepository.findAllByOrderByUserRankAsc(dayId);
        return dailyLeaderboardList;
    }

    @Override
    public List<WeeklyLeaderboard> getWeeklyLeaderboard() {
        long weekId = System.currentTimeMillis()/1000/60/60/24/7;
        List<WeeklyLeaderboard> weeklyLeaderboardList =  weeklyLeaderboardRepository.findAllByOrderByUserRankAsc(weekId);
        return weeklyLeaderboardList;
    }

    @Override
    public List<MonthlyLeaderboard> getMonthlyLeaderboard() {
        long monthId = System.currentTimeMillis()/1000/60/60/24/7/4;
        List<MonthlyLeaderboard> monthlyLeaderboardList = monthlyLeaderboardRepository.findAllByOrderByUserRankAsc(monthId);
        return monthlyLeaderboardList;
    }

    @Override
    public List<ContestLeaderboard> getContestLeaderboard(Integer contestId) {
        List<ContestLeaderboard> contestLeaderboardList=contestLeaderboardRepository.findAllByContestId(contestId);
        return contestLeaderboardList;
    }
}
