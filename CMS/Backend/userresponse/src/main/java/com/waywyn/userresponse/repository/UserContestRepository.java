package com.waywyn.userresponse.repository;

import com.waywyn.userresponse.entity.UserContest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserContestRepository extends MongoRepository<UserContest, Integer> {
    UserContest getByUserIdAndContestId(int userId, int contestId);


    ArrayList<UserContest> getByUserId(int userId);

    ArrayList<UserContest> getByContestId(int contestId);
}
