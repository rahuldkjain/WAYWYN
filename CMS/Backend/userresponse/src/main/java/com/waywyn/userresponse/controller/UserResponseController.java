package com.waywyn.userresponse.controller;

import com.waywyn.userresponse.DTO.JsonResponseDTO;
import com.waywyn.userresponse.DTO.StringResponseDTO;
import com.waywyn.userresponse.DTO.UserResponseDTO;
import com.waywyn.userresponse.service.UserResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/response")
public class UserResponseController {

    @Autowired
    private UserResponseService userResponseService;

    private final static Logger LOGGER =
            Logger.getLogger(UserResponseController.class.getName());

    @PostMapping(value="/user", consumes = {"application/json"})
    public ResponseEntity saveUserResponse(@RequestBody final UserResponseDTO userResponseDTO) {
        try {
            LOGGER.log(Level.INFO, userResponseDTO.toString());
            String dataString = userResponseService.saveUserResponse(userResponseDTO);
            StringResponseDTO data = new StringResponseDTO();
            data.setResponse(dataString);
            LOGGER.log(Level.INFO, data.toString());
            return new ResponseEntity(data,HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value="/user", consumes = {"application/json"})
    public ResponseEntity updateUserResponse(@RequestBody final UserResponseDTO userResponseDTO) {
        try {
            LOGGER.log(Level.INFO, userResponseDTO.toString());
            String dataString = userResponseService.updateUserResponse(userResponseDTO);
            StringResponseDTO data = new StringResponseDTO();
            data.setResponse(dataString);
            LOGGER.log(Level.INFO, data.toString());
            return new ResponseEntity(data, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/contest/{contestId}/{userId}")
    public ResponseEntity contestresponse (@PathVariable("contestId") final int contestId, @PathVariable("userId") final int userId)  {
        try {
            LOGGER.log(Level.INFO, "contestId : "+contestId+" userId : "+userId);
            HashMap<Integer, String> data = userResponseService.contestresponse(contestId, userId);
            if(data != null)
                LOGGER.log(Level.INFO, data.toString());
            else
                LOGGER.log(Level.INFO, "User"+userId+ "has not played contest" + contestId);
            return new ResponseEntity(data, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
