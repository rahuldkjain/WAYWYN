package com.waywyn.userresponse.service;

import com.waywyn.userresponse.DTO.UserResponseDTO;
import com.waywyn.userresponse.entity.UserResponse;

import java.util.HashMap;

public interface UserResponseService {

    String updateUserResponse(UserResponseDTO userResponseDTO) throws Exception;
    String saveUserResponse(UserResponseDTO userResponseDTO) throws Exception;
    HashMap<Integer, String> contestresponse(int contestId, int userId) throws Exception;
}
