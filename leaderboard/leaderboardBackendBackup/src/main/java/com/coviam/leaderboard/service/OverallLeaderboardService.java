package com.coviam.leaderboard.service;

import com.coviam.leaderboard.dto.ContestLeaderboardDTO;
import com.coviam.leaderboard.dto.DailyLeaderboardDTO;
import com.coviam.leaderboard.dto.MonthlyLeaderboardDTO;
import com.coviam.leaderboard.dto.WeeklyLeaderboardDTO;
import com.coviam.leaderboard.entity.ContestLeaderboard;
import com.coviam.leaderboard.entity.DailyLeaderboard;
import com.coviam.leaderboard.entity.MonthlyLeaderboard;
import com.coviam.leaderboard.entity.WeeklyLeaderboard;

import java.util.List;

public interface OverallLeaderboardService {

    List<DailyLeaderboardDTO> getDailyLeaderboard();

    List<WeeklyLeaderboardDTO> getWeeklyLeaderboard();

    List<MonthlyLeaderboardDTO> getMonthlyLeaderboard();

    List<ContestLeaderboardDTO> getContestLeaderboard(Integer contestId);

    List<WeeklyLeaderboardDTO> getWeeklyLeaderboardByWeekId(Integer weekId);

    List<MonthlyLeaderboardDTO> getMonthlyLeaderboardByMonthId(Integer monthId);

    List<Integer> getWeekIds();

    List<Integer> getDayIds();

    List<Integer> getMonthIds();

    List<DailyLeaderboardDTO> getDailyLeaderboardByDayId(Integer dayId);
}
