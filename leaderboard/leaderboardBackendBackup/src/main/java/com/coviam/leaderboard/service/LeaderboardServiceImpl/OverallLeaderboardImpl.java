package com.coviam.leaderboard.service.LeaderboardServiceImpl;

import com.coviam.leaderboard.dto.ContestLeaderboardDTO;
import com.coviam.leaderboard.dto.DailyLeaderboardDTO;
import com.coviam.leaderboard.dto.MonthlyLeaderboardDTO;
import com.coviam.leaderboard.dto.WeeklyLeaderboardDTO;
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

    @Override
    public List<DailyLeaderboardDTO> getDailyLeaderboard() {
        long dayId = System.currentTimeMillis()/1000/60/60/24;
        List<DailyLeaderboard> dailyLeaderboardList= dailyLeaderboardRepository.findAllByOrderByUserScoreDesc(dayId);
        List<DailyLeaderboardDTO> dailyLeaderboardDTOList=new ArrayList<>();
        List<Integer> scores=new ArrayList<>();
        for(DailyLeaderboard dailyLeaderboard:dailyLeaderboardList){
            Integer score =dailyLeaderboard.getScore();
            scores.add(score);
        }
        List<Integer> ranks=findRank(scores);
        int rankIndex=0;
        for(DailyLeaderboard dailyLeaderboard:dailyLeaderboardList){
            DailyLeaderboardDTO dailyLeaderboardDTO=new DailyLeaderboardDTO();
            dailyLeaderboardDTO.setScore(dailyLeaderboard.getScore());
            dailyLeaderboardDTO.setUsername(dailyLeaderboard.getUsername());
            dailyLeaderboardDTO.setUserRank(ranks.get(rankIndex++));
            dailyLeaderboardDTOList.add(dailyLeaderboardDTO);
        }
        return dailyLeaderboardDTOList;
    }

    @Override
    public List<WeeklyLeaderboardDTO> getWeeklyLeaderboard() {
        long weekId = System.currentTimeMillis()/1000/60/60/24/7;
        List<WeeklyLeaderboard> weeklyLeaderboardList= weeklyLeaderboardRepository.findAllByOrderByUserScoreDesc(weekId);
        List<WeeklyLeaderboardDTO> weeklyLeaderboardDTOList=new ArrayList<>();
        List<Integer> scores=new ArrayList<>();
        for(WeeklyLeaderboard weeklyLeaderboard:weeklyLeaderboardList){
            Integer score =weeklyLeaderboard.getScore();
            scores.add(score);
        }
        List<Integer> ranks=findRank(scores);
        int rankIndex=0;
        for(WeeklyLeaderboard weeklyLeaderboard:weeklyLeaderboardList){
            WeeklyLeaderboardDTO weeklyLeaderboardDTO=new WeeklyLeaderboardDTO();
            weeklyLeaderboardDTO.setScore(weeklyLeaderboard.getScore());
            weeklyLeaderboardDTO.setUsername(weeklyLeaderboard.getUsername());
            weeklyLeaderboardDTO.setUserRank(ranks.get(rankIndex++));
            weeklyLeaderboardDTOList.add(weeklyLeaderboardDTO);
        }
        return weeklyLeaderboardDTOList;
    }

    @Override
    public List<MonthlyLeaderboardDTO> getMonthlyLeaderboard() {
        long monthId = System.currentTimeMillis()/1000/60/60/24/7/4;
        List<MonthlyLeaderboard> monthlyLeaderboardList= monthlyLeaderboardRepository.findAllByOrderByScoreDesc(monthId);
        List<MonthlyLeaderboardDTO> monthlyLeaderboardDTOList=new ArrayList<>();
        List<Integer> scores=new ArrayList<>();
        for(MonthlyLeaderboard monthlyLeaderboard:monthlyLeaderboardList){
            Integer score =monthlyLeaderboard.getScore();
            scores.add(score);
        }
        List<Integer> ranks=findRank(scores);
        int rankIndex=0;
        for(MonthlyLeaderboard monthlyLeaderboard:monthlyLeaderboardList){
            MonthlyLeaderboardDTO monthlyLeaderboardDTO=new MonthlyLeaderboardDTO();
            monthlyLeaderboardDTO.setScore(monthlyLeaderboard.getScore());
            monthlyLeaderboardDTO.setUsername(monthlyLeaderboard.getUsername());
            monthlyLeaderboardDTO.setUserRank(ranks.get(rankIndex++));
            monthlyLeaderboardDTOList.add(monthlyLeaderboardDTO);
        }
        return monthlyLeaderboardDTOList;
    }

    @Override
    public List<ContestLeaderboardDTO> getContestLeaderboard(Integer contestId) {
        List<ContestLeaderboard> contestLeaderboardList= contestLeaderboardRepository.findAllByOrderByScoreDesc(contestId);
        List<ContestLeaderboardDTO> contestLeaderboardDTOList=new ArrayList<>();
        List<Integer> scores=new ArrayList<>();
        for(ContestLeaderboard contestLeaderboard:contestLeaderboardList){
            Integer score =contestLeaderboard.getScore();
            scores.add(score);
        }
        List<Integer> ranks=findRank(scores);
        int rankIndex=0;
        for(ContestLeaderboard contestLeaderboard:contestLeaderboardList){
            ContestLeaderboardDTO contestLeaderboardDTO=new ContestLeaderboardDTO();
            contestLeaderboardDTO.setScore(contestLeaderboard.getScore());
            contestLeaderboardDTO.setUsername(contestLeaderboard.getUsername());
            contestLeaderboardDTO.setUserRank(ranks.get(rankIndex++));
            contestLeaderboardDTOList.add(contestLeaderboardDTO);
        }
        return contestLeaderboardDTOList;
    }

    @Override
    public List<WeeklyLeaderboardDTO> getWeeklyLeaderboardByWeekId(Integer weekId) {
        List<WeeklyLeaderboard> weeklyLeaderboardList= weeklyLeaderboardRepository.findAllByOrderByUserScoreDesc(weekId);
        List<WeeklyLeaderboardDTO> weeklyLeaderboardDTOList=new ArrayList<>();
        List<Integer> scores=new ArrayList<>();
        for(WeeklyLeaderboard weeklyLeaderboard:weeklyLeaderboardList){
            Integer score =weeklyLeaderboard.getScore();
            scores.add(score);
        }
        List<Integer> ranks=findRank(scores);
        int rankIndex=0;
        for(WeeklyLeaderboard weeklyLeaderboard:weeklyLeaderboardList){
            WeeklyLeaderboardDTO weeklyLeaderboardDTO=new WeeklyLeaderboardDTO();
            weeklyLeaderboardDTO.setScore(weeklyLeaderboard.getScore());
            weeklyLeaderboardDTO.setUsername(weeklyLeaderboard.getUsername());
            weeklyLeaderboardDTO.setUserRank(ranks.get(rankIndex++));
            weeklyLeaderboardDTOList.add(weeklyLeaderboardDTO);
        }
        return weeklyLeaderboardDTOList;
    }

    @Override
    public List<MonthlyLeaderboardDTO> getMonthlyLeaderboardByMonthId(Integer monthId) {
        List<MonthlyLeaderboard> monthlyLeaderboardList= monthlyLeaderboardRepository.findAllByOrderByScoreDesc(monthId);
        List<MonthlyLeaderboardDTO> monthlyLeaderboardDTOList=new ArrayList<>();
        List<Integer> scores=new ArrayList<>();
        for(MonthlyLeaderboard monthlyLeaderboard:monthlyLeaderboardList){
            Integer score =monthlyLeaderboard.getScore();
            scores.add(score);
        }
        List<Integer> ranks=findRank(scores);
        int rankIndex=0;
        for(MonthlyLeaderboard monthlyLeaderboard:monthlyLeaderboardList){
            MonthlyLeaderboardDTO monthlyLeaderboardDTO=new MonthlyLeaderboardDTO();
            monthlyLeaderboardDTO.setScore(monthlyLeaderboard.getScore());
            monthlyLeaderboardDTO.setUsername(monthlyLeaderboard.getUsername());
            monthlyLeaderboardDTO.setUserRank(ranks.get(rankIndex++));
            monthlyLeaderboardDTOList.add(monthlyLeaderboardDTO);
        }
        return monthlyLeaderboardDTOList;
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
    public List<DailyLeaderboardDTO> getDailyLeaderboardByDayId(Integer dayId) {
        List<DailyLeaderboard> dailyLeaderboardList= dailyLeaderboardRepository.findAllByOrderByUserScoreDesc(dayId);
        List<DailyLeaderboardDTO> dailyLeaderboardDTOList=new ArrayList<>();
        List<Integer> scores=new ArrayList<>();
        for(DailyLeaderboard dailyLeaderboard:dailyLeaderboardList){
            Integer score =dailyLeaderboard.getScore();
            scores.add(score);
        }
        List<Integer> ranks=findRank(scores);
        int rankIndex=0;
        for(DailyLeaderboard dailyLeaderboard:dailyLeaderboardList){
            DailyLeaderboardDTO dailyLeaderboardDTO=new DailyLeaderboardDTO();
            dailyLeaderboardDTO.setScore(dailyLeaderboard.getScore());
            dailyLeaderboardDTO.setUsername(dailyLeaderboard.getUsername());
            dailyLeaderboardDTO.setUserRank(ranks.get(rankIndex++));
            dailyLeaderboardDTOList.add(dailyLeaderboardDTO);
        }
        return dailyLeaderboardDTOList;
    }
    private List<Integer> findRank(List<Integer> scores) {
        List<Integer> rankList=new ArrayList<Integer>();
        int rank=0;
        int previousScore=-1;
        int usersWithSameScore=0;
        Integer newRank;
        for (Integer score:scores){
            if(score!=previousScore){
                rank+=usersWithSameScore;
                newRank=++rank;
                rankList.add(newRank);
                usersWithSameScore=0;
            }else{
                newRank=rank;
                rankList.add(newRank);
                usersWithSameScore++;
            }
            previousScore=score;
        }
        return rankList;
    }
}
