package com.coviam.leaderboard.service.LeaderboardServiceImpl;

import com.coviam.leaderboard.entity.DailyLeaderboard;
import com.coviam.leaderboard.entity.MonthlyLeaderboard;
import com.coviam.leaderboard.entity.WeeklyLeaderboard;
import com.coviam.leaderboard.service.OverallLeaderboardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverallLeaderboardImpl implements OverallLeaderboardService {
    @Override
    public List<DailyLeaderboard> getDailyLeaderboard() {
        return null;
    }

    @Override
    public List<WeeklyLeaderboard> getWeeklyLeaderboard() {
        return null;
    }

    @Override
    public List<MonthlyLeaderboard> getMonthlyLeaderboard() {
        return null;
    }
}
