package com.waywyn.userresponse.controller;

import com.waywyn.userresponse.DTO.*;
import com.waywyn.userresponse.service.DynamicTimeTrackService;
import com.waywyn.userresponse.service.UserContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/usercontest")
public class UserContestController {

    @Autowired
    private UserContestService userContestService;

    @Autowired
    private DynamicTimeTrackService dynamicTimeTrackService;

    public JsonResponseDTO convertData (Object data) {
        JsonResponseDTO jsonResponseDTO = new JsonResponseDTO();
        jsonResponseDTO.setData(data);
        jsonResponseDTO.setError("");
        jsonResponseDTO.setMessage("Data Sent");
        return jsonResponseDTO;
    }

    @PostMapping(value = "/addtime", consumes = {"application/json"})
    public ResponseEntity addTime (@RequestBody DynamicTimeTrackDTO dynamicTimeTrackDTO) {
        try {
            String dataString = dynamicTimeTrackService.addTime(dynamicTimeTrackDTO);
            StringResponseDTO data = new StringResponseDTO();
            data.setResponse(dataString);
            return new ResponseEntity(data, HttpStatus.OK);
            //JsonResponseDTO jsonResponseDTO = convertData(data);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/userresult", consumes = {"application/json"})
    public ResponseEntity userResult (@RequestBody UserResultRecieveDTO userResultRecieveDTO) {
        try {
            UserResultDTO data = userContestService.userResult(userResultRecieveDTO);
            //JsonResponseDTO jsonResponseDTO = convertData(data);
            return new ResponseEntity(data,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/useractive")
    public ResponseEntity userActive (@RequestParam final int userId) {
        try {
            ArrayList<ContestDefinitionDTO> data = userContestService.userActive(userId);
            //JsonResponseDTO jsonResponseDTO = convertData(data);
            return new ResponseEntity(data,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
