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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserContestServiceImpl implements UserContestService {

    @Autowired
    private UserContestRepository userContestRepository;
    @Autowired
    private UserResponseRepository userResponseRepository;

    private final static Logger LOGGER =
            Logger.getLogger(UserContestServiceImpl.class.getName());

    @Override
    public UserResultDTO userResult(UserResultRecieveDTO userResultRecieveDTO) throws Exception {

        LOGGER.log(Level.INFO,"Generating result for"+userResultRecieveDTO.getUsername()+" Contest ID "+userResultRecieveDTO.toString());
        HashMap<Integer,AnswerDTO> answerDTOHashMap = new HashMap<>();
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
        try {
            answerDTOHashMap = restTemplate.getForObject(url, HashMap.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }

        UserContest userContest = userContestRepository.getByUserIdAndContestId(userResultRecieveDTO.getUserId(),userResultRecieveDTO.getContestId());
        ArrayList<UserResponse> responses = userResponseRepository.getByUcId(userContest.getUcId());
        if(userContest.getType().toLowerCase().equals("static")) {
            for (UserResponse it : responses) {
                if (it.getResponse() == null) {
                    LOGGER.log(Level.WARNING,"Null Pointer in userResult it.getResponse()");
                    throw new Exception("User response is absent");
                }
                else if (it.getResponse().equals("s")) {
                    LOGGER.log(Level.WARNING,"Skip response is still present in userResult");
                    throw new Exception("User has a skipped response");
                } else {
                    answerDTO = mapper.convertValue(answerDTOHashMap.get(String.valueOf(it.getQuestionId())),AnswerDTO.class);
                    if (it.getResponse().toLowerCase().equals(answerDTO.getAnswer().toLowerCase())) {
                        difficulty = answerDTO.getDifficultyType();
                        if (difficulty.toLowerCase().equals("easy"))
                            it.setScore(1);
                        else if (difficulty.toLowerCase().equals("medium"))
                            it.setScore(3);
                        else if (difficulty.toLowerCase().equals("hard"))
                            it.setScore(5);
                        else {
                            LOGGER.log(Level.WARNING,"Wrong difficulty in userResult");
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
        if(!userContest.getSkipFlag())
            totalScore+=5;

        //Rest template to send data to leader board
        BeanUtils.copyProperties(userContest,userResultToLeaderboardDTO);
        userResultToLeaderboardDTO.setQuestions(userResponseLeaderboardDTOS);
        HttpEntity<UserResultToLeaderboardDTO> request = new HttpEntity<>(userResultToLeaderboardDTO);
        url = "http://10.177.7.144:8080/leaderboard/static";
        try {
            URI location = restTemplate.postForLocation(url, request);
            LOGGER.log(Level.INFO,"Rest template to static leaderboard");
        } catch (HttpClientErrorException e) {
            LOGGER.log(Level.WARNING,"Rest template to static leaderboard ERROR");
            e.printStackTrace();
        }

        userResultDTO.setCorrectAnswers(correctAnswer);
        userResultDTO.setTotalScore(totalScore);
        return userResultDTO;
    }

    @Override
    public String dynamicQuesResult(DynamicTimeTrack dynamicTimeTrack) throws Exception {
        LOGGER.log(Level.INFO,"Generating result for "+dynamicTimeTrack.getContestId()+" Question Id "+dynamicTimeTrack.getQuestionId());
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
            if(userResponse.getResponse() == null || userResponse.getResponse().equals("s")) {
                LOGGER.log(Level.WARNING,"Null pointer exception in dynamicQuesResult in getResponse");
                throw new Exception("response is either null or or skipped");
            }
            else {
                if (userResponse.getResponse().toLowerCase().equals(dynamicTimeTrack.getAnswer().toLowerCase()) && userResponse.getTime().compareTo(dynamicTimeTrack.getStartTime()) >= 0 && userResponse.getTime().compareTo(dynamicTimeTrack.getEndTime()) <= 0) {
                    difficulty = dynamicTimeTrack.getDifficulty();
                    if (difficulty.toLowerCase().equals("easy"))
                        userResponse.setScore(1);
                    else if (difficulty.toLowerCase().equals("medium"))
                        userResponse.setScore(3);
                    else if (difficulty.toLowerCase().equals("hard"))
                        userResponse.setScore(5);
                    else {
                        LOGGER.log(Level.WARNING,"Wrong difficulty in dynamicQuesResult");
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
        try {
            URI location = restTemplate.postForLocation(url, request);
            LOGGER.log(Level.INFO,"Rest template to static leaderboard");
        } catch (HttpClientErrorException e) {
            LOGGER.log(Level.WARNING,"Rest template to static leaderboard ERROR");
            e.printStackTrace();
        }

        LOGGER.log(Level.INFO,"Calculation and sending of score success");
        return "Calculating and send of score successful in dynamicQuestion";
    }

    @Override
    public ArrayList<ContestDefinitionDTO> userActive(int userId) {
        ArrayList<UserContest> contestIds = userContestRepository.getByUserId(userId);
        ArrayList<ContestDefinitionDTO> contestObjectsArray = new ArrayList<>();
        ContestDefinitionDTO contestDefinitionDTO = new ContestDefinitionDTO();
        for (UserContest it: contestIds) {

            //Rest Template to get contests using ContestIds
            String url = "http://10.177.7.130:8080/contest/getcontestdefinition?contestId="+it.getContestId();
            RestTemplate restTemplate = new RestTemplate();
            try {
                contestDefinitionDTO = restTemplate.getForObject(url, ContestDefinitionDTO.class);
            } catch (HttpClientErrorException e) {
                e.printStackTrace();
            }
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
