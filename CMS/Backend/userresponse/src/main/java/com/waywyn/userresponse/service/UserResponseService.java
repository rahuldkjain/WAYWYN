package com.waywyn.userresponse.service;

import com.waywyn.userresponse.DTO.UserResponseDTO;
import com.waywyn.userresponse.entity.UserResponse;

import java.util.HashMap;

public interface UserResponseService {

    String updateUserResponse(UserResponseDTO userResponseDTO);
    String saveUserResponse(UserResponseDTO userResponseDTO);
    HashMap<Integer, String> contestresponse(int contestId, int userId);
}
