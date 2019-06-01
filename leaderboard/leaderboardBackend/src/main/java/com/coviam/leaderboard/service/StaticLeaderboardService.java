package com.coviam.leaderboard.service;

import com.coviam.leaderboard.entity.ContestLeaderboard;
import com.coviam.leaderboard.entity.Winner;
import com.coviam.leaderboard.model.CMSStaticRequest;

import java.util.List;

public interface StaticLeaderboardService {

    List<Winner> getStaticLeaderboard(Integer userId, Integer contestId,Integer noOfRecords);

    String insertStaticData(CMSStaticRequest cmsStaticRequest);
}
