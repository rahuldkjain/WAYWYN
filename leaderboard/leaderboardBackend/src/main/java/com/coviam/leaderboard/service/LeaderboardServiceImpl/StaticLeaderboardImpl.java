package com.coviam.leaderboard.service.LeaderboardServiceImpl;

import com.coviam.leaderboard.entity.ContestLeaderboard;
import com.coviam.leaderboard.model.CMSStaticRequest;
import com.coviam.leaderboard.service.StaticLeaderboardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaticLeaderboardImpl implements StaticLeaderboardService {
    @Override
    public List<ContestLeaderboard> getStaticLeaderboard(Integer userId, Integer contestId) {
        return null;
    }

    @Override
    public String insertStaticData(CMSStaticRequest cmsStaticRequest) {
        return null;
    }
}
