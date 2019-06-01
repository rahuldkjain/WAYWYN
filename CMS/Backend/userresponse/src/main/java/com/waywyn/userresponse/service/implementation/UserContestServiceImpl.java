package com.waywyn.userresponse.service.implementation;

import com.waywyn.userresponse.DTO.*;
import com.waywyn.userresponse.entity.DynamicTimeTrack;
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
        HashMap<Integer,AnswerDTO> answerDTOHashMap;
        UserResultDTO userResultDTO = new UserResultDTO();
        UserResultToLeaderboardDTO userResultToLeaderboardDTO = new UserResultToLeaderboardDTO();
        HashMap<Integer,Integer> questionsScore = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        Date date = new Date();
        String difficulty;
        int totalScore = 0;
        int correctAnswer = 0;

        //Rest template for getting answers from contest
        String url = "http://ip:port/ontest/answers?contestId="+userResultRecieveDTO.getContestId();
        answerDTOHashMap = restTemplate.getForObject(url,HashMap.class);

        UserContest userContest = userContestRepository.getByUserIdAndContestId(userResultRecieveDTO.getUserId(),userResultRecieveDTO.getContestId());
        ArrayList<UserResponse> responses = userResponseRepository.getByUcId(userContest.getUcId());
        if(userContest.getType()=="static") {
            for (UserResponse it : responses) {
                if (it.getResponse() == null)
                    System.out.println("Handle NullPointer Exception");
                else if (it.getResponse().equals("S")) {
                    //to add skip exception
                    System.out.println("Handle Skip response error");
                } else {
                    if (it.getResponse().equals(answerDTOHashMap.get(it.getQuestionId()).getAnswer())) {
                        difficulty = answerDTOHashMap.get(it.getQuestionId()).getDifficultyLevel();
                        if (difficulty == "easy")
                            it.setScore(1);
                        else if (difficulty == "average")
                            it.setScore(3);
                        else if (difficulty == "hard")
                            it.setScore(5);
                        else {
                            System.out.println("Handle wrong difficulty type exception");
                        }
                        ++correctAnswer;
                    }
                    else {
                        it.setScore(0);
                    }
                }
                questionsScore.put(it.getQuestionId(), it.getScore());
                totalScore += it.getScore();
            }
        }
        else if(userContest.getType() == "dynamic") {
            for (UserResponse it : responses) {
                if(it.getScore() != 0)
                    correctAnswer++;
                totalScore += it.getScore();
            }
        }
        userContest.setEndDate(date);
        userContestRepository.save(userContest);


        //Rest template to send data to leader board
        BeanUtils.copyProperties(userContest,userResultToLeaderboardDTO);
        userResultToLeaderboardDTO.setQuestions(questionsScore);
        HttpEntity<UserResultToLeaderboardDTO> request = new HttpEntity<>(userResultToLeaderboardDTO);
        url = "http://ip:port/leaderboard/static";
        URI location = restTemplate.postForLocation(url,request);
        System.out.println("exception handling of location"+ location);


        userResultDTO.setCorrectAnswers(correctAnswer);
        userResultDTO.setTotalScore(totalScore);
        return userResultDTO;
    }

    @Override
    public String dynamicQuesResult(DynamicTimeTrack dynamicTimeTrack) {
        DynamicQuesResultToLeaderboardDTO dynamicQuesResultToLeaderboardDTO = new DynamicQuesResultToLeaderboardDTO();
        DynamicResponseDTO dynamicResponseDTO = new DynamicResponseDTO();
        ArrayList<DynamicResponseDTO> dynamicResponseDTOS = new ArrayList<>();
        ArrayList<UserContest> userContests = userContestRepository.getByContestId(dynamicTimeTrack.getContestId());
        UserResponse userResponse;
        String difficulty;
        BeanUtils.copyProperties(userContests.get(0),dynamicQuesResultToLeaderboardDTO);
        dynamicQuesResultToLeaderboardDTO.setDate(new Date());
        dynamicQuesResultToLeaderboardDTO.setQuestionID(dynamicTimeTrack.getQuestionId());
        for (UserContest it: userContests) {
            BeanUtils.copyProperties(it,dynamicResponseDTO);
            userResponse = userResponseRepository.getByUcIdAndQuestionId(it.getUcId(),dynamicTimeTrack.getQuestionId());
            if(userResponse.getResponse() == null || userResponse.getResponse() == "S") {
                System.out.println("Handle null or skip exception");
            }
            else {
                if (userResponse.getResponse().equals(dynamicTimeTrack.getAnswer()) && userResponse.getTime().compareTo(dynamicTimeTrack.getStartTime()) >= 0 && userResponse.getTime().compareTo(dynamicTimeTrack.getEndTime()) <= 0) {
                    difficulty = dynamicTimeTrack.getDifficulty();
                    if (difficulty == "easy")
                        userResponse.setScore(1);
                    else if (difficulty == "average")
                        userResponse.setScore(3);
                    else if (difficulty == "hard")
                        userResponse.setScore(5);
                    else {
                        System.out.println("Handle wrong difficulty type exception");
                    }
                }
                else {
                    userResponse.setScore(0);
                }
            }
            userResponse = userResponseRepository.save(userResponse);
            dynamicResponseDTO.setScore(userResponse.getScore());
            dynamicResponseDTOS.add(dynamicResponseDTO);
        }
        dynamicQuesResultToLeaderboardDTO.setRepsonse(dynamicResponseDTOS);

        //Rest Template to send score to Leaderboard
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DynamicQuesResultToLeaderboardDTO> request = new HttpEntity<>(dynamicQuesResultToLeaderboardDTO);
        String url = "http://ip:port/leaderboard/dynamic";
        URI location = restTemplate.postForLocation(url,request);
        System.out.println("exception handling of location"+ location);

        return "Calculating and send of score successful";
    }

    @Override
    public ArrayList<ContestDefinitionDTO> userActive(int userId) {
        ArrayList<UserContest> contestIds = userContestRepository.getByUserId(userId);
        ArrayList<ContestDefinitionDTO> contestObjectsArray = new ArrayList<>();
        ContestDefinitionDTO contestDefinitionDTO;
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
