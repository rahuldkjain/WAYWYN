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

@RestController
@RequestMapping("/response")
public class UserResponseController {

    @Autowired
    private UserResponseService userResponseService;

    public JsonResponseDTO convertData (Object data) {
        JsonResponseDTO jsonResponseDTO = new JsonResponseDTO();
        jsonResponseDTO.setData(data);
        jsonResponseDTO.setError("");
        jsonResponseDTO.setMessage("Data Sent");
        return jsonResponseDTO;
    }

    @PostMapping(value="/user", consumes = {"application/json"})
    public ResponseEntity saveUserResponse(@RequestBody final UserResponseDTO userResponseDTO) {
        //JsonResponseDTO jsonResponseDTO = new JsonResponseDTO();
        try {
            String dataString = userResponseService.saveUserResponse(userResponseDTO);
            StringResponseDTO data = new StringResponseDTO();
            data.setResponse(dataString);
          //  jsonResponseDTO = convertData(data);
            return new ResponseEntity(data,HttpStatus.OK);
        } catch (Exception e) {
            //jsonResponseDTO.setError(e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value="/user", consumes = {"application/json"})
    public ResponseEntity updateUserResponse(@RequestBody final UserResponseDTO userResponseDTO) {
        try {
            String dataString = userResponseService.updateUserResponse(userResponseDTO);
            StringResponseDTO data = new StringResponseDTO();
            data.setResponse(dataString);
            return new ResponseEntity(data, HttpStatus.OK);
            //JsonResponseDTO jsonResponseDTO = convertData(data);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/contest/{contestId}/{userId}")
    public ResponseEntity contestresponse (@PathVariable("contestId") final int contestId, @PathVariable("userId") final int userId)  {
        try {
            HashMap<Integer, String> data = userResponseService.contestresponse(contestId, userId);
            //JsonResponseDTO jsonResponseDTO = convertData(data);
            return new ResponseEntity(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
