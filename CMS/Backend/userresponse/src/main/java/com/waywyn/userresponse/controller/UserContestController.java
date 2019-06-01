package com.waywyn.userresponse.controller;

import com.waywyn.userresponse.DTO.ContestDefinitionDTO;
import com.waywyn.userresponse.DTO.DynamicTimeTrackDTO;
import com.waywyn.userresponse.DTO.UserResultDTO;
import com.waywyn.userresponse.DTO.UserResultRecieveDTO;
import com.waywyn.userresponse.service.DynamicTimeTrackService;
import com.waywyn.userresponse.service.UserContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/usercontest")
public class UserContestController {

    @Autowired
    private UserContestService userContestService;

    @Autowired
    private DynamicTimeTrackService dynamicTimeTrackService;

    @PostMapping(value = "/addtime", consumes = {"application/json"})
    public String addTime (@RequestBody DynamicTimeTrackDTO dynamicTimeTrackDTO) {
        return dynamicTimeTrackService.addTime(dynamicTimeTrackDTO);
    }

    @PostMapping(value="/userresult", consumes = {"application/json"})
    public UserResultDTO userResult (@RequestBody UserResultRecieveDTO userResultRecieveDTO) {
        return userContestService.userResult(userResultRecieveDTO);
    }

    @GetMapping("/useractive")
    public ArrayList<ContestDefinitionDTO> userActive (@RequestParam final int userId) {
        return userContestService.userActive(userId);
    }

}
