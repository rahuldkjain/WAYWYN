package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.WeeklyLeaderboard;
import com.coviam.leaderboard.pkclasses.WeeklyLeaderboardPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyLeaderboardRepository extends CrudRepository<WeeklyLeaderboard,WeeklyLeaderboardPK> {

}
