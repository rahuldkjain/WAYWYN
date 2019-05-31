package com.waywyn.userresponse.repository;

import com.waywyn.userresponse.entity.UserResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserResponseRepository extends MongoRepository<UserResponse, Integer> {
    UserResponse getByUcIdAndQuestionId(int ucId, int questionId);

    ArrayList<UserResponse> getByUcId(int ucId);
}
