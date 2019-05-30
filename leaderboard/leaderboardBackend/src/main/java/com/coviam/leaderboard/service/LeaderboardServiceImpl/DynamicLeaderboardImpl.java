package com.coviam.leaderboard.service.LeaderboardServiceImpl;

import com.coviam.leaderboard.entity.Contest;
import com.coviam.leaderboard.entity.Question;
import com.coviam.leaderboard.entity.UserScore;
import com.coviam.leaderboard.model.CMSDynamicRequest;
import com.coviam.leaderboard.model.UserDynamicResponse;
import com.coviam.leaderboard.pkclasses.UserScorePK;
import com.coviam.leaderboard.repository.ContestRepository;
import com.coviam.leaderboard.repository.QuestionRepository;
import com.coviam.leaderboard.repository.UserScoreRepository;
import com.coviam.leaderboard.service.DynamicLeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicLeaderboardImpl implements DynamicLeaderboardService {

    @Autowired
    ContestRepository contestRepositoryDynamic;

    @Autowired
    QuestionRepository questionRepositoryDynamic;

    @Autowired
    UserScoreRepository userScoreRepositoryDynamic;

    @Override
    public String insertDynamicData(CMSDynamicRequest cmsDynamicRequest) {
        //update contest table
        Contest contest=new Contest(cmsDynamicRequest.getContestId(),cmsDynamicRequest.getType(),cmsDynamicRequest.getCategory(),cmsDynamicRequest.getDate());
        contestRepositoryDynamic.save(contest);

        //user response table
        int correctCount=0;
        List<UserDynamicResponse> responses=cmsDynamicRequest.getResponse();
        for(UserDynamicResponse response:responses){
            if(userScoreRepositoryDynamic.exists(new UserScorePK(response.getUserId(),cmsDynamicRequest.getContestId()))){
                UserScore userScore=userScoreRepositoryDynamic.findOne(new UserScorePK(response.getUserId(),cmsDynamicRequest.getContestId()));
                userScore.setScore(userScore.getScore()+response.getScore());
                userScoreRepositoryDynamic.save(userScore);
                if(response.getScore()>0){
                    correctCount+=1;
                }
            }
            else{
                UserScore userScore=new UserScore(response.getUserId(),cmsDynamicRequest.getContestId(),response.getUsername(),response.getScore());
                userScoreRepositoryDynamic.save(userScore);
                if(response.getScore()>0){
                    correctCount+=1;
                }
            }
        }
        //question table
        if(questionRepositoryDynamic.exists(cmsDynamicRequest.getqId())){
            Question question=questionRepositoryDynamic.findOne(cmsDynamicRequest.getqId());
            question.setCorrect_count(question.getCorrect_count()+correctCount);
            questionRepositoryDynamic.save(question);
        }else {
            questionRepositoryDynamic.save(new Question(cmsDynamicRequest.getqId(),correctCount));
        }


        return "success";
    }

    @Override
    public List<CMSDynamicRequest> getDynamicLeaderboard(Integer userId, Integer contestid) {
        return null;
    }
}
