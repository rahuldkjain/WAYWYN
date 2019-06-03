package com.waywyn.userresponse.scheduler;

import com.waywyn.userresponse.entity.DynamicTimeTrack;
import com.waywyn.userresponse.service.DynamicTimeTrackService;
import com.waywyn.userresponse.service.UserContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class TaskScheduler {

    @Autowired
    DynamicTimeTrackService dynamicTimeTrackService;

    @Autowired
    UserContestService userContestService;

    @Scheduled(fixedDelay = 60000)
    public void scheduleToCalcScore() throws Exception {
        DynamicTimeTrack dynamicTimeTrack = dynamicTimeTrackService.getQuestion();
        if(dynamicTimeTrack != null) {
            userContestService.dynamicQuesResult(dynamicTimeTrack);
        }
        if(dynamicTimeTrack != null)
            dynamicTimeTrackService.deleteAll(dynamicTimeTrack.getContestId());
    }

}
