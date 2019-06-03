package com.coviam.leaderboard.service.ReportServiceImpl;

import com.coviam.leaderboard.dto.Winner;
import com.coviam.leaderboard.entity.*;
import com.coviam.leaderboard.repository.*;
import com.coviam.leaderboard.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    QuestionDetailsRepository questionDetailsRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    UserScoreRepository userScoreRepository;

    @Autowired
    ContestLeaderboardRepository contestLeaderboardRepository;
    @Override
    public List<QuestionDetails> mostCorrectlyAnswered() {
        List<Question> questionList = questionRepository.findMaxCountQuestions();
        List<QuestionDetails> questionDetailsList = new ArrayList<QuestionDetails>();
        for(Question question: questionList){
            QuestionDetails questionDetails = questionDetailsRepository.findByQuestionId(question.getqId());
            if(questionDetails != null){
                questionDetailsList.add(questionDetails);
            }
        }

        return questionDetailsList;
    }

    @Override
    public List<Contest> numberOfActiveContests() {
        java.util.Date today=new java.util.Date();
        Date dateVal=new Date(today.getTime());
        List<Contest> contestList = contestRepository.findActiveContest(dateVal);

        return contestList;
    }

    @Override
    public List<UserScore> numberOfActiveUsers(Integer contestId) {
        List<UserScore> userScoreList = userScoreRepository.findByContestId(contestId);
        return userScoreList;
    }

    @Override
    public List<Winner> getWinners(Integer contestId) {
        List<ContestLeaderboard> winnerList = contestLeaderboardRepository.findAllByUserRank(contestId);
        List<Winner> winners=new ArrayList<Winner>();
        for(ContestLeaderboard winner:winnerList){
            Winner winnerObject=new Winner();
            winnerObject.setScore(winner.getScore());
            winnerObject.setUsername(winner.getUsername());
            winnerObject.setUserRank(winner.getUserRank());
            winners.add(winnerObject);
        }

        return winners;
    }
}
