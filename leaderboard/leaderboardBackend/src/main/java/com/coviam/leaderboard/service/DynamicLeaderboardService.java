package com.coviam.leaderboard.service;

import com.coviam.leaderboard.entity.Winner;
import com.coviam.leaderboard.model.CMSDynamicRequest;

import java.util.List;

public interface DynamicLeaderboardService {

    String insertDynamicData(CMSDynamicRequest cmsDynamicRequest);

    List<Winner> getDynamicLeaderboard(Integer userId, Integer contestid, Integer noOfRecords);
}
