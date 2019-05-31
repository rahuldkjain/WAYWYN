package com.waywyn.userresponse.controller;

import com.waywyn.userresponse.DTO.UserResponseDTO;
import com.waywyn.userresponse.service.UserResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/response")
public class UserResponseController {

    @Autowired
    private UserResponseService userResponseService;

    @PostMapping(value="/user", consumes = {"application/json"})
    public String saveUserResponse(@RequestBody final UserResponseDTO userResponseDTO) {
        return userResponseService.saveUserResponse(userResponseDTO);
    }

    @PutMapping(value="/user", consumes = {"application/json"})
    public String updateUserResponse(@RequestBody final UserResponseDTO userResponseDTO) {
        return userResponseService.updateUserResponse(userResponseDTO);
    }

    @GetMapping("/contest/{contestId}/{userId}")
    public HashMap<Integer,String> contestresponse (@PathVariable("contestId") final int contestId, @PathVariable("userId") final int userId)  {
        return  userResponseService.contestresponse(contestId,userId);
    }

}
