package com.waywyn.userresponse.service.implementation;

import com.waywyn.userresponse.DTO.ContestDefinitionDTO;
import com.waywyn.userresponse.DTO.UserResponseDTO;
import com.waywyn.userresponse.entity.UserContest;
import com.waywyn.userresponse.entity.UserResponse;
import com.waywyn.userresponse.repository.UserContestRepository;
import com.waywyn.userresponse.repository.UserResponseRepository;
import com.waywyn.userresponse.service.UserResponseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class UserResponseServiceImpl implements UserResponseService {

    @Autowired
    private UserContestRepository userContestRepository;
    @Autowired
    private UserResponseRepository userResponseRepository;

    @Override
    public String saveUserResponse(UserResponseDTO userResponseDTO) {
        UserResponse userResponse = new UserResponse();
        UserContest userContest = userContestRepository.getByUserIdAndContestId(userResponseDTO.getUserId(),userResponseDTO.getContestId());
        String url = new String();
        if(userContest == null) {
            userContest.setContestId(userResponseDTO.getContestId());
            userContest.setSkipFlag(false);
            userContest.setUserId(userResponseDTO.getUserId());
            userContest.setUsername(userResponseDTO.getUsername());


            //Rest template to add type and category from contest microservice
            ContestDefinitionDTO contestDefinitionDTO;
            url = "http://ip:port/sd?contestId="+userContest.getContestId();
            RestTemplate restTemplate = new RestTemplate();
            contestDefinitionDTO = restTemplate.getForObject(url,ContestDefinitionDTO.class);
            userContest.setCategory(contestDefinitionDTO.getCategoryOfContest());
            userContest.setType(contestDefinitionDTO.getContestType());

        }
        BeanUtils.copyProperties(userResponseDTO,userResponse);
        userResponse.setUcId(userContest.getUcId());
        if(userResponse.getResponse()=="S")
            userContest.setSkipFlag(true);
        userContestRepository.save(userContest);
        if(userResponseRepository.save(userResponse)!=null) {
            return "Response stored";
        }
        else {
            return "Failed to store response ";
        }
    }

    @Override
    public String updateUserResponse(UserResponseDTO userResponseDTO) {
        UserContest userContest = userContestRepository.getByUserIdAndContestId(userResponseDTO.getUserId(),userResponseDTO.getContestId());
        UserResponse userResponse;
        if(userContest == null) {
            return "Wrong response update";
        }
        else {
            userResponse = userResponseRepository.getByUcIdAndQuestionId(userContest.getUcId(),userResponseDTO.getQuestionId());
            if(userResponse.getResponse() != "S") {
                return "Question is not Skipped";
            }
            else {
                userResponse.setResponse(userResponseDTO.getResponse());
                userResponse = userResponseRepository.save(userResponse);
            }
        }
        if(userResponse == null) {
            return "Unable to update response";
        }
        else {
            return "Response Updated";
        }
    }

    @Override
    public HashMap<Integer, String> contestresponse(int contestId, int userId) {
        UserContest userContest = userContestRepository.getByUserIdAndContestId(userId,contestId);
        ArrayList<UserResponse> responses = userResponseRepository.getByUcId(userContest.getUcId());
        HashMap<Integer,String> userResponses = new HashMap<>();
        for (UserResponse it: responses) {
            userResponses.put(it.getQuestionId(),it.getResponse());
        }
        return userResponses;
    }
}
