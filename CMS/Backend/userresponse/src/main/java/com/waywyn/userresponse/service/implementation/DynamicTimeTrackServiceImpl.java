package com.waywyn.userresponse.service.implementation;

import com.waywyn.userresponse.DTO.DynamicTimeTrackDTO;
import com.waywyn.userresponse.entity.DynamicTimeTrack;
import com.waywyn.userresponse.repository.DynamicTimeTrackRepository;
import com.waywyn.userresponse.service.DynamicTimeTrackService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class DynamicTimeTrackServiceImpl implements DynamicTimeTrackService {

    @Autowired
    private DynamicTimeTrackRepository dynamicTimeTrackRepository;

    @Override
    public String addTime(DynamicTimeTrackDTO dynamicTimeTrackDTO) {
        DynamicTimeTrack dynamicTimeTrack = dynamicTimeTrackRepository.findByContestIdAndQuestionId(dynamicTimeTrackDTO.getContestId(),dynamicTimeTrackDTO.getQuestionId());
        if(dynamicTimeTrack != null) {
            System.out.println("Throw already exist exception in addTime");
        }
        BeanUtils.copyProperties(dynamicTimeTrackDTO,dynamicTimeTrack);
        dynamicTimeTrack.setResultDone(false);
        dynamicTimeTrack = dynamicTimeTrackRepository.save(dynamicTimeTrack);
        if(dynamicTimeTrack == null) {
            System.out.println("throw error in addTime");
            return "Error in addTime";
        }
        else {
            System.out.println("Data Successfully Stored in addTime");
            return "Data Successfully Stored in addTime";
        }
    }

    @Override
    public DynamicTimeTrack getQuestion () {
        Date date = new Date();
        DynamicTimeTrack dynamicTimeTrack = dynamicTimeTrackRepository.findFirst1ByResultDoneAndStartTimeLessThanAndEndTimeLessThan(false,date,date);
        if(dynamicTimeTrack == null) {
            System.out.println("throw error in getQuestion");
        }
        return dynamicTimeTrack;
    }

    @Override
    public String deleteAll(int contestId) {
        ArrayList<DynamicTimeTrack> dynamicTimeTrack = dynamicTimeTrackRepository.findByResultDone(false);
        if(dynamicTimeTrack == null) {
            dynamicTimeTrackRepository.deleteAll();
            System.out.println("Entries Deleted in deleteAll");
            return "Entries Deleted in deleteAll";
        }
        else {
            System.out.println("Few Question remaining to delete in deleteAll");
            return "Few Question remaining to delete in deleteAll";
        }
    }

}
