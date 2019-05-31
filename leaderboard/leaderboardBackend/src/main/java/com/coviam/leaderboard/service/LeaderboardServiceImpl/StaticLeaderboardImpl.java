package com.coviam.leaderboard.service.LeaderboardServiceImpl;

import com.coviam.leaderboard.entity.Contest;
import com.coviam.leaderboard.entity.ContestLeaderboard;
import com.coviam.leaderboard.entity.Question;
import com.coviam.leaderboard.entity.UserScore;
import com.coviam.leaderboard.model.CMSStaticRequest;
import com.coviam.leaderboard.model.UserQuestionResponse;
import com.coviam.leaderboard.repository.ContestRepository;
import com.coviam.leaderboard.repository.QuestionRepository;
import com.coviam.leaderboard.repository.UserScoreRepository;
import com.coviam.leaderboard.service.StaticLeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaticLeaderboardImpl implements StaticLeaderboardService {
    @Autowired
    ContestRepository contestRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserScoreRepository userScoreRepository;

    @Override
    public List<ContestLeaderboard> getStaticLeaderboard(Integer userId, Integer contestId) {
        //todo same in overall as well.
        return null;
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
