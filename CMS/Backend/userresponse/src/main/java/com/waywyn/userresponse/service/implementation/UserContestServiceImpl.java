package com.waywyn.userresponse.service.implementation;

import com.waywyn.userresponse.DTO.*;
import com.waywyn.userresponse.entity.UserContest;
import com.waywyn.userresponse.entity.UserResponse;
import com.waywyn.userresponse.repository.UserContestRepository;
import com.waywyn.userresponse.repository.UserResponseRepository;
import com.waywyn.userresponse.service.UserContestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Service
public class UserContestServiceImpl implements UserContestService {

    @Autowired
    private UserContestRepository userContestRepository;
    @Autowired
    private UserResponseRepository userResponseRepository;

    @Override
    public UserResultDTO userResult(UserResultRecieveDTO userResultRecieveDTO) {
        HashMap<Integer,AnswerDTO> answerDTOHashMap = new HashMap<>();
        UserResultDTO userResultDTO = new UserResultDTO();
        UserResultToLeaderboardDTO userResultToLeaderboardDTO = new UserResultToLeaderboardDTO();
        HashMap<Integer,Integer> questionsScore = new HashMap<>();
        Date date = new Date();
        String difficulty;
        int totalScore = 0;
        int correctAnswer = 0;
        //Rest template for getting answers from contest
        UserContest userContest = userContestRepository.getByUserIdAndContestId(userResultRecieveDTO.getUserId(),userResultRecieveDTO.getContestId());
        ArrayList<UserResponse> responses = userResponseRepository.getByUcId(userContest.getUcId());
        for (UserResponse it: responses) {
            if(it.getResponse()==null)
                System.out.println("Handle NullPointer Exception");
            else if(it.getResponse().equals("S")) {
                //to add skip exception
                System.out.println("Handle Skip response error");
            }
            else {
                if(it.getResponse().equals(answerDTOHashMap.get(it.getQuestionId()).getAnswer())) {
                    difficulty = answerDTOHashMap.get(it.getQuestionId()).getDifficultyLevel();
                    if( difficulty == "easy")
                        it.setScore(1);
                    else if ( difficulty == "average")
                        it.setScore(3);
                    else if (difficulty == "hard")
                        it.setScore(5);
                    else {
                        System.out.println("Handle wrong difficulty type exception");
                    }
                    ++correctAnswer;
                }
            }
            questionsScore.put(it.getQuestionId(),it.getScore());
            totalScore+=it.getScore();
        }
        userContest.setEndDate(date);
        userContestRepository.save(userContest);


        //Rest template to send data to leader board
        BeanUtils.copyProperties(userContest,userResultToLeaderboardDTO);
        userResultToLeaderboardDTO.setQuestions(questionsScore);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<UserResultToLeaderboardDTO> request = new HttpEntity<>(userResultToLeaderboardDTO);
        String url = "http://ip:port/leaderboard/static";
        URI location = restTemplate.postForLocation(url,request);
        System.out.println("exception handling of location"+ location);


        userResultDTO.setCorrectAnswers(correctAnswer);
        userResultDTO.setTotalScore(totalScore);
        return userResultDTO;
    }

    @Override
    public ArrayList<ContestDefinitionDTO> userActive(int userId) {
        ArrayList<UserContest> contestIds = userContestRepository.getByUserId(userId);
        ArrayList<ContestDefinitionDTO> contestObjectsArray = new ArrayList<>();
        ContestDefinitionDTO contestDefinitionDTO = new ContestDefinitionDTO();
        for (UserContest it: contestIds) {

            //Rest Template to get contests using ContestIds
            String url = "http://ip:port/?contestId="+it.getContestId();
            RestTemplate restTemplate = new RestTemplate();
            contestDefinitionDTO = restTemplate.getForObject(url,ContestDefinitionDTO.class);
            contestObjectsArray.add(contestDefinitionDTO);

        }
        return contestObjectsArray;
    }
}
