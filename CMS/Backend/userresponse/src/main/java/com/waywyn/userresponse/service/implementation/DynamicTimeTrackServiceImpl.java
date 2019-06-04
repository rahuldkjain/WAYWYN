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
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DynamicTimeTrackServiceImpl implements DynamicTimeTrackService {

    @Autowired
    private DynamicTimeTrackRepository dynamicTimeTrackRepository;

    private final static Logger LOGGER =
            Logger.getLogger(DynamicTimeTrackRepository.class.getName());

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
            LOGGER.log(Level.WARNING, "Null Pointer Error in addTime");
            return "Error in addTime";
        }
        else {
            LOGGER.log(Level.INFO,"Data Stored : "+dynamicTimeTrack.toString());
            return "Data Successfully Stored in addTime";
        }
    }

    @Override
    public DynamicTimeTrack getQuestion () {
        Date date = new Date();
        DynamicTimeTrack dynamicTimeTrack = dynamicTimeTrackRepository.findFirst1ByResultDoneAndStartTimeLessThanAndEndTimeLessThan(false,date,date);
        if(dynamicTimeTrack == null) {
            LOGGER.log(Level.WARNING,"Null Pointer in getQuestion");
        }
        return dynamicTimeTrack;
    }

    @Override
    public String deleteAll(int contestId) {
        ArrayList<DynamicTimeTrack> dynamicTimeTrack = dynamicTimeTrackRepository.findByResultDone(false);
        if(dynamicTimeTrack == null) {
            dynamicTimeTrackRepository.deleteAll();
            LOGGER.log(Level.INFO,"All entries deleted in deleteAll");
            return "Entries Deleted in deleteAll";
        }
        else {
            LOGGER.log(Level.INFO,"Few Questions remaining to delete");
            return "Few Question remaining to delete in deleteAll";
        }
    }

}
