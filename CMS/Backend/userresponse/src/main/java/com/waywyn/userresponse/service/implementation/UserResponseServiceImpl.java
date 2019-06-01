package com.waywyn.userresponse.service.implementation;

import com.waywyn.userresponse.DTO.ContestDefinitionDTO;
import com.waywyn.userresponse.DTO.UserResponseDTO;
import com.waywyn.userresponse.entity.Counter;
import com.waywyn.userresponse.entity.UserContest;
import com.waywyn.userresponse.entity.UserResponse;
import com.waywyn.userresponse.repository.CounterRepository;
import com.waywyn.userresponse.repository.UserContestRepository;
import com.waywyn.userresponse.repository.UserResponseRepository;
import com.waywyn.userresponse.service.CounterService;
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
    @Autowired
    private CounterRepository counterRepository;
    @Autowired
    private CounterService counterService;

    @Override
    public String saveUserResponse(UserResponseDTO userResponseDTO) {
        UserResponse userResponse = new UserResponse();
        UserContest userContest = userContestRepository.getByUserIdAndContestId(userResponseDTO.getUserId(),userResponseDTO.getContestId());
        String url;
        if(userContest == null) {
            userContest = new UserContest();
            userContest.setContestId(userResponseDTO.getContestId());
            userContest.setSkipFlag(false);
            userContest.setUserId(userResponseDTO.getUserId());
            userContest.setUsername(userResponseDTO.getUsername());


            //Rest template to add type and category from contest microservice
            ContestDefinitionDTO contestDefinitionDTO;
            url = "http://10.177.7.130:8080/contest/getcontestdefinition?contestId="+userContest.getContestId();
            RestTemplate restTemplate = new RestTemplate();
            contestDefinitionDTO = restTemplate.getForObject(url,ContestDefinitionDTO.class);
            userContest.setCategory(contestDefinitionDTO.getCategoryName());
            userContest.setType(contestDefinitionDTO.getContestType());
            userContest.setUcId(counterService.genNextSequence("userContest"));
        }
        BeanUtils.copyProperties(userResponseDTO,userResponse);
        userResponse.setUcId(userContest.getUcId());
        if(userResponse.getResponse()=="S")
            userContest.setSkipFlag(true);
        userContestRepository.save(userContest);
        userResponse.setUrId(counterService.genNextSequence("userResponse"));
        if(userResponseRepository.save(userResponse)!=null) {
            return "Response stored in saveUserResponse";
        }
        else {
            return "Failed to store response in saveUserResponse";
        }
    }

    @Override
    public String updateUserResponse(UserResponseDTO userResponseDTO) {
        UserContest userContest = userContestRepository.getByUserIdAndContestId(userResponseDTO.getUserId(),userResponseDTO.getContestId());
        UserResponse userResponse;
        if(userContest == null) {
            return "Wrong response update in updateUserResponse";
        }
        else {
            userResponse = userResponseRepository.getByUcIdAndQuestionId(userContest.getUcId(),userResponseDTO.getQuestionId());
            if(!userResponse.getResponse().equals("S")) {
                return "Question is not Skipped in updateUserResponse";
            }
            else {
                userResponse.setResponse(userResponseDTO.getResponse());
                userResponse = userResponseRepository.save(userResponse);
            }
        }
        if(userResponse == null) {
            return "Unable to update response in updateUserResponse";
        }
        else {
            return "Response Updated in updateUserResponse";
        }
    }

    @Override
    public HashMap<Integer, String> contestresponse(int contestId, int userId) {
        UserContest userContest = userContestRepository.getByUserIdAndContestId(userId,contestId);
        if(userContest != null) {
            ArrayList<UserResponse> responses = userResponseRepository.getByUcId(userContest.getUcId());
            if (responses != null) {
                HashMap<Integer, String> userResponses = new HashMap<>();
                for (UserResponse it : responses) {
                    userResponses.put(it.getQuestionId(), it.getResponse());
                }
                return userResponses;
            }
        }
        return null;
    }
}
