package com.waywyn.userresponse.service;

import com.waywyn.userresponse.DTO.ContestDefinitionDTO;
import com.waywyn.userresponse.DTO.UserContestDTO;
import com.waywyn.userresponse.DTO.UserResultDTO;
import com.waywyn.userresponse.DTO.UserResultRecieveDTO;
import com.waywyn.userresponse.entity.DynamicTimeTrack;

import java.util.ArrayList;

public interface UserContestService {

    //UserContestDTO addUserContest(UserContestDTO userContestDTO);
    //UserContestDTO updateUserContest(UserContestDTO userContestDTO);
    //UserContestDTO deleteUserContest(int ucId);
    //UserContestDTO getUserContest(int ucId);


    UserResultDTO userResult(UserResultRecieveDTO userResultRecieveDTO) throws Exception;

    String dynamicQuesResult(DynamicTimeTrack dynamicTimeTrack) throws Exception;

    ArrayList<ContestDefinitionDTO> userActive(int userId);
}
