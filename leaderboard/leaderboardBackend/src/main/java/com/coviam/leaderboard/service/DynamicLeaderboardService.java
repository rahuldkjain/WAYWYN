package com.coviam.leaderboard.service;

import com.coviam.leaderboard.model.CMSDynamicRequest;

import java.util.List;

public interface DynamicLeaderboardService {
    String insertDynamicData(CMSDynamicRequest cmsDynamicRequest);
    List<CMSDynamicRequest> getDynamicLeaderboard(Integer userId, Integer contestid);
}
