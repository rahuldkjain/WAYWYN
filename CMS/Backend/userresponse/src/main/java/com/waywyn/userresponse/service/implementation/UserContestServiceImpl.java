package com.waywyn.userresponse.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public UserResultDTO userResult(UserResultRecieveDTO userResultRecieveDTO) throws Exception {
        HashMap<Integer,AnswerDTO> answerDTOHashMap;
        UserResultDTO userResultDTO = new UserResultDTO();
        UserResultToLeaderboardDTO userResultToLeaderboardDTO = new UserResultToLeaderboardDTO();
        UserResponseLeaderboardDTO userResponseLeaderboardDTO = new UserResponseLeaderboardDTO();
        ArrayList<UserResponseLeaderboardDTO> userResponseLeaderboardDTOS = new ArrayList<>();
        AnswerDTO answerDTO = new AnswerDTO();
        RestTemplate restTemplate = new RestTemplate();
        Date date = new Date();
        String difficulty;
        int totalScore = 0;
        int correctAnswer = 0;
        ObjectMapper mapper = new ObjectMapper();
        //Rest template for getting answers from contest
        String url = "http://10.177.7.130:8080/contest/answers?contestId="+userResultRecieveDTO.getContestId();
        answerDTOHashMap = restTemplate.getForObject(url,HashMap.class);

        UserContest userContest = userContestRepository.getByUserIdAndContestId(userResultRecieveDTO.getUserId(),userResultRecieveDTO.getContestId());
        ArrayList<UserResponse> responses = userResponseRepository.getByUcId(userContest.getUcId());
        if(userContest.getType().toLowerCase().equals("static")) {
            for (UserResponse it : responses) {
                if (it.getResponse() == null) {
                    System.out.println("Handle NullPointer Exception in userResult");
                    throw new Exception("User response is absent");
                }
                else if (it.getResponse().equals("s")) {
                    //to add skip exception
                    System.out.println("Handle Skip response error in userResult");
                    throw new Exception("User has a skipped response");
                } else {
                    answerDTO = mapper.convertValue(answerDTOHashMap.get(String.valueOf(it.getQuestionId())),AnswerDTO.class);
                    if (it.getResponse().equals(answerDTO.getAnswer().toLowerCase())) {
                        difficulty = answerDTO.getDifficultyType();
                        if (difficulty.toLowerCase().equals("easy"))
                            it.setScore(1);
                        else if (difficulty.toLowerCase().equals("average"))
                            it.setScore(3);
                        else if (difficulty.toLowerCase().equals("hard"))
                            it.setScore(5);
                        else {
                            System.out.println("Handle wrong difficulty type exception in userResult");
                            throw new Exception("Wrong difficulty for question");
                        }
                        ++correctAnswer;
                    }
                    else {
                        it.setScore(0);
                    }
                }
                userResponseLeaderboardDTO = new UserResponseLeaderboardDTO();
                userResponseLeaderboardDTO.setqId(it.getQuestionId());
                userResponseLeaderboardDTO.setScore(it.getScore());
                userResponseLeaderboardDTOS.add(userResponseLeaderboardDTO);
                //questionsScore.put(it.getQuestionId(), it.getScore());
                totalScore += it.getScore();
                userResponseRepository.save(it);
            }
        }
        else if(userContest.getType().toLowerCase().equals("dynamic")) {
            for (UserResponse it : responses) {
                if(it.getScore() != 0)
                    correctAnswer++;
                totalScore += it.getScore();
                userResponseRepository.save(it);
            }
        }
        userContest.setEndDate(date);
        userContestRepository.save(userContest);


        //Rest template to send data to leader board
        BeanUtils.copyProperties(userContest,userResultToLeaderboardDTO);
        userResultToLeaderboardDTO.setQuestions(userResponseLeaderboardDTOS);
        HttpEntity<UserResultToLeaderboardDTO> request = new HttpEntity<>(userResultToLeaderboardDTO);
        url = "http://10.177.7.144:8080/leaderboard/static";
        URI location = restTemplate.postForLocation(url,request);

        userResultDTO.setCorrectAnswers(correctAnswer);
        userResultDTO.setTotalScore(totalScore);
        return userResultDTO;
    }

    @Override
    public String dynamicQuesResult(DynamicTimeTrack dynamicTimeTrack) throws Exception {
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
            if(userResponse.getResponse() == null || userResponse.getResponse() == "s") {
                System.out.println("Handle null or skip exception in dynamicQuesResult");
                throw new Exception("response is either null or or skipped");
            }
            else {
                if (userResponse.getResponse().equals(dynamicTimeTrack.getAnswer().toLowerCase()) && userResponse.getTime().compareTo(dynamicTimeTrack.getStartTime()) >= 0 && userResponse.getTime().compareTo(dynamicTimeTrack.getEndTime()) <= 0) {
                    difficulty = dynamicTimeTrack.getDifficulty();
                    if (difficulty.toLowerCase().equals("easy"))
                        userResponse.setScore(1);
                    else if (difficulty.toLowerCase().equals("average"))
                        userResponse.setScore(3);
                    else if (difficulty.toLowerCase().equals("hard"))
                        userResponse.setScore(5);
                    else {
                        System.out.println("Handle wrong difficulty type exception in dynamicQuestion");
                        throw new Exception("Wrong difficulty for question");
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
        String url = "http://10.177.7.144:8080/leaderboard/dynamic";
        URI location = restTemplate.postForLocation(url,request);

        System.out.println("Calculating and send of score successful in dynamicQuestion");
        return "Calculating and send of score successful in dynamicQuestion";
    }

    @Override
    public ArrayList<ContestDefinitionDTO> userActive(int userId) {
        ArrayList<UserContest> contestIds = userContestRepository.getByUserId(userId);
        ArrayList<ContestDefinitionDTO> contestObjectsArray = new ArrayList<>();
        ContestDefinitionDTO contestDefinitionDTO;
        for (UserContest it: contestIds) {

            //Rest Template to get contests using ContestIds
            String url = "http://10.177.7.130:8080/contest/getcontestdefinition?contestId="+it.getContestId();
            RestTemplate restTemplate = new RestTemplate();
            contestDefinitionDTO = restTemplate.getForObject(url,ContestDefinitionDTO.class);
            if(it.getEndDate() == null) {
                contestDefinitionDTO.setEnded("incomplete");
            }
            else {
                contestDefinitionDTO.setEnded("complete");
            }
            contestObjectsArray.add(contestDefinitionDTO);
        }
        return contestObjectsArray;
    }


}
