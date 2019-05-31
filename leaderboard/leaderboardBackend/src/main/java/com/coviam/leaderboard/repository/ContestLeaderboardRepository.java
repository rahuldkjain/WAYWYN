package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.ContestLeaderboard;
import com.coviam.leaderboard.entity.Winner;
import com.coviam.leaderboard.pkclasses.ContestLeaderboardPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestLeaderboardRepository extends CrudRepository<ContestLeaderboard,ContestLeaderboardPK> {

        @Query(value = "select * from contest_leaderboard where user_rank=1 and contest_id=?1",nativeQuery = true)
        List<ContestLeaderboard> findAllByUserRank(Integer contestId);
}
