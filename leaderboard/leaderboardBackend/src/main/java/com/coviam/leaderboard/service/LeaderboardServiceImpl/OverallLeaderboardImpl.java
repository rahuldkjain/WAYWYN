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
import org.joda.time.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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

    MutableDateTime epoch = new MutableDateTime();
    DateTime now = new DateTime();

    @Override
    public List<DailyLeaderboard> getDailyLeaderboard() {
        epoch.setDate(0); //Set to Epoch time
        Days days = Days.daysBetween(epoch, now);
        long dayId = days.getDays();
        List<DailyLeaderboard> dailyLeaderboardList= dailyLeaderboardRepository.findAllByOrderByUserRankAsc(dayId);
        return dailyLeaderboardList;
    }

    @Override
    public List<WeeklyLeaderboard> getWeeklyLeaderboard() {
        epoch.setDate(0);
        Weeks weeks = Weeks.weeksBetween(epoch, now);
        long weekId = weeks.getWeeks();
        List<WeeklyLeaderboard> weeklyLeaderboardList =  weeklyLeaderboardRepository.findAllByOrderByUserRankAsc(weekId);
        return weeklyLeaderboardList;
    }

    @Override
    public List<MonthlyLeaderboard> getMonthlyLeaderboard() {
        MutableDateTime epoch = new MutableDateTime();
        epoch.setDate(0);
        DateTime now = new DateTime();
        Months months = Months.monthsBetween(epoch, now);
        long monthId = months.getMonths();
        System.out.println(monthId+"+++++++++++++++++++++");
        List<MonthlyLeaderboard> monthlyLeaderboardList = monthlyLeaderboardRepository.findAllByOrderByUserRankAsc(monthId);

        return monthlyLeaderboardList;
    }

    @Override
    public List<ContestLeaderboard> getContestLeaderboard(Integer contestId) {
        List<ContestLeaderboard> contestLeaderboardList=contestLeaderboardRepository.findAllBycontestIdOrderByUserRankAsc(contestId);
        return contestLeaderboardList;
    }

    @Override
    public List<WeeklyLeaderboard> getWeeklyLeaderboardByWeekId(Integer weekId) {
        List<WeeklyLeaderboard> weeklyLeaderboardList =  weeklyLeaderboardRepository.findAllByOrderByUserRankAsc(weekId);
        return weeklyLeaderboardList;
    }

    @Override
    public List<MonthlyLeaderboard> getMonthlyLeaderboardByMonthId(Integer monthId) {
        List<MonthlyLeaderboard> monthlyLeaderboardList = monthlyLeaderboardRepository.findAllByOrderByUserRankAsc(monthId);
        return monthlyLeaderboardList;
    }

    @Override
    public List<Integer> getWeekIds() {
        List<Integer> weekIdList=weeklyLeaderboardRepository.findDistinctWeekId();
        return weekIdList;
    }

    @Override
    public List<Integer> getDayIds() {
        List<Integer> dayIdList=dailyLeaderboardRepository.findDistinctDayId();
        return dayIdList;
    }

    @Override
    public List<Integer> getMonthIds() {
        List<Integer> monthIdList=monthlyLeaderboardRepository.findDistinctMonthId();
        return monthIdList;
    }

    @Override
    public List<DailyLeaderboard> getDailyLeaderboardByDayId(Integer dayId) {
        List<DailyLeaderboard> dailyLeaderboardList= dailyLeaderboardRepository.findAllByOrderByUserRankAsc(dayId);
        return dailyLeaderboardList;
    }
}
