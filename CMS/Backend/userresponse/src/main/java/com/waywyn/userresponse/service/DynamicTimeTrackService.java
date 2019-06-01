package com.waywyn.userresponse.service;

import com.waywyn.userresponse.DTO.DynamicTimeTrackDTO;
import com.waywyn.userresponse.entity.DynamicTimeTrack;

public interface DynamicTimeTrackService {
    String addTime(DynamicTimeTrackDTO dynamicTimeTrackDTO);

    DynamicTimeTrack getQuestion();

    String deleteAll(int contestId);
}
