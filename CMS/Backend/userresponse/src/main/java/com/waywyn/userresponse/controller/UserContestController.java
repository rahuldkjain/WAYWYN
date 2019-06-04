package com.waywyn.userresponse.controller;

import com.waywyn.userresponse.DTO.*;
import com.waywyn.userresponse.entity.UserContest;
import com.waywyn.userresponse.service.DynamicTimeTrackService;
import com.waywyn.userresponse.service.UserContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/usercontest")
public class UserContestController {

    @Autowired
    private UserContestService userContestService;

    @Autowired
    private DynamicTimeTrackService dynamicTimeTrackService;

    private final static Logger LOGGER =
            Logger.getLogger(UserContestController.class.getName());

    @PostMapping(value = "/addtime", consumes = {"application/json"})
    public ResponseEntity addTime (@RequestBody DynamicTimeTrackDTO dynamicTimeTrackDTO) {
        try {
            LOGGER.log(Level.INFO, dynamicTimeTrackDTO.toString());
            String dataString = dynamicTimeTrackService.addTime(dynamicTimeTrackDTO);
            StringResponseDTO data = new StringResponseDTO();
            data.setResponse(dataString);
            LOGGER.log(Level.INFO, data.toString());
            return new ResponseEntity(data, HttpStatus.OK);
            //JsonResponseDTO jsonResponseDTO = convertData(data);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING,e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/userresult", consumes = {"application/json"})
    public ResponseEntity userResult (@RequestBody UserResultRecieveDTO userResultRecieveDTO) {
        try {
            LOGGER.log(Level.INFO, userResultRecieveDTO.toString());
            UserResultDTO data = userContestService.userResult(userResultRecieveDTO);
            //JsonResponseDTO jsonResponseDTO = convertData(data);
            LOGGER.log(Level.INFO, data.toString());
            return new ResponseEntity(data,HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING,e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/useractive")
    public ResponseEntity userActive (@RequestParam final int userId) {
        try {
            LOGGER.log(Level.INFO, String.valueOf(userId));
            ArrayList<ContestDefinitionDTO> data = userContestService.userActive(userId);
            LOGGER.log(Level.INFO, data.toString());
            return new ResponseEntity(data,HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
