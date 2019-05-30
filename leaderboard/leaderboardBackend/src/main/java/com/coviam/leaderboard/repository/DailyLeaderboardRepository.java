package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.DailyLeaderboard;
import com.coviam.leaderboard.pkclasses.DailyLeaderboardPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyLeaderboardRepository extends CrudRepository<DailyLeaderboard,DailyLeaderboardPK> {

}
