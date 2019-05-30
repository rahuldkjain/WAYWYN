package com.coviam.leaderboard.service.LeaderboardServiceImpl;

import com.coviam.leaderboard.model.CMSDynamicRequest;
import com.coviam.leaderboard.service.DynamicLeaderboardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicLeaderboardImpl implements DynamicLeaderboardService {

    @Override
    public String insertDynamicData(CMSDynamicRequest cmsDynamicRequest) {
        return null;
    }

    @Override
    public List<CMSDynamicRequest> getDynamicLeaderboard(Integer userId, Integer contestid) {
        return null;
    }
}
