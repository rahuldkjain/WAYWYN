package com.waywyn.userresponse.repository;

import com.waywyn.userresponse.entity.UserContest;
import com.waywyn.userresponse.entity.UserResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserContestRepository extends MongoRepository<UserContest, Integer> {
    UserContest getByUserIdAndContestId(int userId, int contestId);


    ArrayList<UserContest> getByUserId(int userId);
}
