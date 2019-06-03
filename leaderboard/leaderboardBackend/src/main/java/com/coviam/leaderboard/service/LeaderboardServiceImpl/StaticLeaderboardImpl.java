package com.coviam.leaderboard.service.LeaderboardServiceImpl;

import com.coviam.leaderboard.entity.*;
import com.coviam.leaderboard.model.CMSStaticRequest;
import com.coviam.leaderboard.model.UserQuestionResponse;
import com.coviam.leaderboard.repository.ContestLeaderboardRepository;
import com.coviam.leaderboard.repository.ContestRepository;
import com.coviam.leaderboard.repository.QuestionRepository;
import com.coviam.leaderboard.repository.UserScoreRepository;
import com.coviam.leaderboard.service.StaticLeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaticLeaderboardImpl implements StaticLeaderboardService {
    @Autowired
    ContestRepository contestRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserScoreRepository userScoreRepository;

    @Autowired
    ContestLeaderboardRepository contestLeaderboardRepository;

    @Override
    public List<Winner> getStaticLeaderboard(Integer userId, Integer contestId,Integer noOfRecords) {

        List<ContestLeaderboard> contestLeaderboardList=contestLeaderboardRepository.findAllBycontestIdOrderByUserRankAsc(contestId);
        List<Winner> winnerList=new ArrayList<Winner>();
        Winner userRecord=new Winner();
        int noOfRecordsCopied=0;
        boolean isUserFound=false;
        for(ContestLeaderboard user:contestLeaderboardList){
            if(noOfRecordsCopied>=noOfRecords && isUserFound){
                break;
            }
            Winner winner=new Winner();
            winner.setScore(user.getScore());
            winner.setUsername(user.getUsername());
            winner.setUserRank(user.getUserRank());
            if(userId==user.getUserId()){
                userRecord.setUsername(user.getUsername());
                userRecord.setUserRank(user.getUserRank());
                userRecord.setScore(user.getScore());
                isUserFound=true;
            }
            if(noOfRecordsCopied<noOfRecords){
                winnerList.add(winner);
            }
            noOfRecordsCopied++;
        }

        winnerList.add(userRecord);
        return winnerList;
    }

    @Override
    public String insertStaticData(CMSStaticRequest cmsStaticRequest) {
        //updates contest table
        Contest contest=new Contest(cmsStaticRequest.getContestId(),cmsStaticRequest.getType(),cmsStaticRequest.getCategory(),cmsStaticRequest.getContestEndDate());
        contestRepository.save(contest);

        //updates userscore table
        int score=0;
        List<UserQuestionResponse> responses = cmsStaticRequest.getQuestions();
        for(UserQuestionResponse response:responses){
            score+=response.getScore();
            if(response.getScore()>0){
                //updates question correctcount
                Question question=questionRepository.findOne(response.getqId());
                if(question!=null){
                    Question question1=new Question(response.getqId(),question.getCorrect_count()+1);
                    questionRepository.save(question1);
                }
                else{
                    Question question1=new Question(response.getqId(),1);
                    questionRepository.save(question1);
                }
            }

        }
        if(!cmsStaticRequest.isSkip()){
            score+=5;
        }
        //updates user score
        UserScore userScore=new UserScore(cmsStaticRequest.getUserId(),cmsStaticRequest.getContestId(),cmsStaticRequest.getUsername(),score,cmsStaticRequest.getEndDate());
        userScoreRepository.save(userScore);

        return "success";
    }
}
