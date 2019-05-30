package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.MonthlyLeaderboard;
import com.coviam.leaderboard.pkclasses.MonthlyLeaderboardPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyLeaderboardRepository extends CrudRepository<MonthlyLeaderboard,MonthlyLeaderboardPK> {

}
