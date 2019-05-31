package com.coviam.leaderboard.service;

import com.coviam.leaderboard.entity.ContestLeaderboard;
import com.coviam.leaderboard.model.CMSStaticRequest;

import java.util.List;

public interface StaticLeaderboardService {
    List<ContestLeaderboard> getStaticLeaderboard(Integer userId, Integer contestId);
    String insertStaticData(CMSStaticRequest cmsStaticRequest);
}
