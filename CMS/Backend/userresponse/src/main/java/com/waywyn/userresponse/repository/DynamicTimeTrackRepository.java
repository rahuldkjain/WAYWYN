package com.waywyn.userresponse.repository;

import com.waywyn.userresponse.entity.DynamicTimeTrack;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.Date;

public interface DynamicTimeTrackRepository extends MongoRepository<DynamicTimeTrack,Integer> {
    DynamicTimeTrack findByContestIdAndQuestionId(int contestId, int questionId);

    //DynamicTimeTrack findByResultDoneAndStartTimeLessThanAndEndTimeGreaterThan(boolean b, Date date, Date date1);

    ArrayList<DynamicTimeTrack> findByResultDone(boolean b);

    DynamicTimeTrack findFirst1ByResultDoneAndStartTimeLessThanAndEndTimeLessThan(boolean b, Date date, Date date1);
}
