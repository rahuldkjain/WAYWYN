package com.waywyn.userresponse.service.implementation;

import com.waywyn.userresponse.DTO.ContestDefinitionDTO;
import com.waywyn.userresponse.DTO.UserResponseDTO;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
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
    public String saveUserResponse(UserResponseDTO userResponseDTO) throws Exception {
        if(userResponseDTO == null || userResponseDTO.getResponse() == null || userResponseDTO.getResponse().equals("")) {
            System.out.println("handle empty response exception");
            throw new Exception("Your Response Object is null");
        }
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
            ContestDefinitionDTO contestDefinitionDTO = new ContestDefinitionDTO();
            url = "http://10.177.7.130:8080/contest/getcontestdefinition?contestId="+userContest.getContestId();
            RestTemplate restTemplate = new RestTemplate();
            try {
                contestDefinitionDTO = restTemplate.getForObject(url, ContestDefinitionDTO.class);
            } catch (HttpClientErrorException e) {
                e.printStackTrace();
            }
            userContest.setCategory(contestDefinitionDTO.getCategoryName());
            userContest.setType(contestDefinitionDTO.getContestType());
            userContest.setUcId(counterService.genNextSequence("userContest"));
        }
        BeanUtils.copyProperties(userResponseDTO,userResponse);
        userResponse.setUcId(userContest.getUcId());
        if(userResponse.getResponse()=="s")
            userContest.setSkipFlag(true);
        userContestRepository.save(userContest);
        UserResponse userResponse1 = userResponseRepository.findByUcIdAndQuestionId(userResponse.getUcId(),userResponse.getQuestionId());
        if(userResponse1 != null) {
            System.out.println("Handle response already here exception");
            throw new Exception("Your Response is already present");
        }
        userResponse.setUrId(counterService.genNextSequence("userResponse"));
        userResponse.setTime(new Date());
        if(userResponseRepository.save(userResponse)!=null) {
            System.out.println("Response stored in saveUserResponse");
            return "Response is stored";
        }
        else {
            System.out.println("Failed to store response in saveUserResponse");
            throw new Exception("Failed to save response");
        }
    }

    @Override
    public String updateUserResponse(UserResponseDTO userResponseDTO) throws Exception {
        UserContest userContest = userContestRepository.getByUserIdAndContestId(userResponseDTO.getUserId(),userResponseDTO.getContestId());
        UserResponse userResponse;
        if(userContest == null) {
            System.out.println("Wrong response update in updateUserResponse");
            throw new Exception("No such contest is present");
        }
        else {
            userResponse = userResponseRepository.getByUcIdAndQuestionId(userContest.getUcId(),userResponseDTO.getQuestionId());
            if(!userResponse.getResponse().equals("s")) {
                System.out.println("Question is not Skipped in updateUserResponse");
                throw new Exception("Question is not skipped earlier");
            }
            else {
                userResponse.setResponse(userResponseDTO.getResponse());
                userResponse = userResponseRepository.save(userResponse);
            }
        }
        if(userResponse == null) {
            System.out.println("Unable to update response in updateUserResponse");
            throw new Exception("Failed to update response");
        }
        else {
            System.out.println("Response Updated in updateUserResponse");
            return "Response Updated in updateUserResponse";
        }
    }

    @Override
    public HashMap<Integer, String> contestresponse(int contestId, int userId) throws Exception {
        UserContest userContest = userContestRepository.getByUserIdAndContestId(userId,contestId);
        HashMap<Integer, String> userResponses = new HashMap<>();

        if(userContest != null) {
            if(userContest.getEndDate() != null && userContest.getEndDate().compareTo(new Date()) < 0)
                userResponses.put(0,"Ended");
            else {
                ArrayList<UserResponse> responses = userResponseRepository.getByUcId(userContest.getUcId());
                if (responses != null) {

                    for (UserResponse it : responses) {
                        userResponses.put(it.getQuestionId(), it.getResponse());
                    }
                }
            }
            return userResponses;
        }
        return null;
    }
}
