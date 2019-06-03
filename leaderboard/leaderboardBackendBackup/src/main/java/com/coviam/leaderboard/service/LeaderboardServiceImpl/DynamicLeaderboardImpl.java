package com.coviam.leaderboard.service.LeaderboardServiceImpl;

import com.coviam.leaderboard.dto.Winner;
import com.coviam.leaderboard.entity.*;
import com.coviam.leaderboard.model.CMSDynamicRequest;
import com.coviam.leaderboard.model.UserDynamicResponse;
import com.coviam.leaderboard.pkclasses.UserScorePK;
import com.coviam.leaderboard.repository.ContestLeaderboardRepository;
import com.coviam.leaderboard.repository.ContestRepository;
import com.coviam.leaderboard.repository.QuestionRepository;
import com.coviam.leaderboard.repository.UserScoreRepository;
import com.coviam.leaderboard.service.DynamicLeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicLeaderboardImpl implements DynamicLeaderboardService {

    @Autowired
    ContestRepository contestRepositoryDynamic;

    @Autowired
    QuestionRepository questionRepositoryDynamic;

    @Autowired
    UserScoreRepository userScoreRepositoryDynamic;

    @Autowired
    ContestLeaderboardRepository contestLeaderboardRepository;

    @Override
    public String insertDynamicData(CMSDynamicRequest cmsDynamicRequest) {
        //update contest table

        Contest contest=new Contest(cmsDynamicRequest.getContestId(),cmsDynamicRequest.getType(),cmsDynamicRequest.getCategory(),cmsDynamicRequest.getDate());
        contestRepositoryDynamic.save(contest);

        //user response table
        int correctCount=0;
        List<UserDynamicResponse> responses=cmsDynamicRequest.getResponse();
        for(UserDynamicResponse response:responses){
            UserScore userScore=userScoreRepositoryDynamic.findOne(new UserScorePK(response.getUserId(),cmsDynamicRequest.getContestId()));
            if(userScore!=null){
                userScore.setScore(userScore.getScore()+response.getScore());
                userScore.setUserEndDate(cmsDynamicRequest.getDate());
                userScoreRepositoryDynamic.save(userScore);
                if(response.getScore()>0){
                    correctCount+=1;
                }
            }
            else{
                UserScore userScore2=new UserScore(response.getUserId(),cmsDynamicRequest.getContestId(),response.getUsername(),response.getScore(),cmsDynamicRequest.getDate());
                userScoreRepositoryDynamic.save(userScore2);
                if(response.getScore()>0){
                    correctCount+=1;
                }
            }
        }
        //question table
        Question question=questionRepositoryDynamic.findOne(cmsDynamicRequest.getqId());
        if(question!=null){
            question.setCorrect_count(question.getCorrect_count()+correctCount);
            questionRepositoryDynamic.save(question);
        }else {
            questionRepositoryDynamic.save(new Question(cmsDynamicRequest.getqId(),correctCount));
        }
        return "success";
    }

    @Override
    public List<Winner> getDynamicLeaderboard(Integer userId, Integer contestId, Integer noOfrecords ) {
        List<ContestLeaderboard> contestLeaderboardList=contestLeaderboardRepository.findAllBycontestIdOrderByUserRankAsc(contestId);
        List<Winner> winnerList=new ArrayList<Winner>();
        Winner userRecord=new Winner();
        int noOfRecordsCopied=0;
        boolean isUserFound=false;
        for(ContestLeaderboard user:contestLeaderboardList){
            if(noOfRecordsCopied>=noOfrecords && isUserFound){
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
            if(noOfRecordsCopied<noOfrecords){
                winnerList.add(winner);
            }
            noOfRecordsCopied++;
        }
        if(userRecord!=null){
            winnerList.add(userRecord);
        }
        return winnerList;

    }
}
