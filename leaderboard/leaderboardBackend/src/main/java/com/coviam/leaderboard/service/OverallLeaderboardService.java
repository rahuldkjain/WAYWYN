package com.coviam.leaderboard.service;

import com.coviam.leaderboard.entity.ContestLeaderboard;
import com.coviam.leaderboard.entity.DailyLeaderboard;
import com.coviam.leaderboard.entity.MonthlyLeaderboard;
import com.coviam.leaderboard.entity.WeeklyLeaderboard;

import java.util.List;

public interface OverallLeaderboardService {

    List<DailyLeaderboard> getDailyLeaderboard();

    List<WeeklyLeaderboard> getWeeklyLeaderboard();

    List<MonthlyLeaderboard> getMonthlyLeaderboard();

}
