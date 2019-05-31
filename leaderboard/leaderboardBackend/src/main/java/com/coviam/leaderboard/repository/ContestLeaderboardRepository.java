package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.ContestLeaderboard;
import com.coviam.leaderboard.pkclasses.ContestLeaderboardPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestLeaderboardRepository extends CrudRepository<ContestLeaderboard,ContestLeaderboardPK> {


}
