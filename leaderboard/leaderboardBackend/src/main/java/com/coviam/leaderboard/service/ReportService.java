package com.coviam.leaderboard.service;

import com.coviam.leaderboard.entity.Contest;
import com.coviam.leaderboard.entity.QuestionDetails;
import com.coviam.leaderboard.entity.UserScore;
import com.coviam.leaderboard.entity.Winner;

import java.util.List;

public interface ReportService {
    public List<QuestionDetails> mostCorrectlyAnswered();
    public List<Contest> numberOfActiveContests();
    public List<UserScore> numberOfActiveUsers(Integer contestId);
    public List<Winner> getWinners(Integer contestId);
}
