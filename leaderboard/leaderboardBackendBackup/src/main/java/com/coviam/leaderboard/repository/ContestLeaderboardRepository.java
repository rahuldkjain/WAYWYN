package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.ContestLeaderboard;
import com.coviam.leaderboard.pkclasses.ContestLeaderboardPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestLeaderboardRepository extends CrudRepository<ContestLeaderboard,ContestLeaderboardPK> {


        List<ContestLeaderboard> findAllByContestId(Integer contestId);

        @Query(value = "select * from contest_leaderboard where contest_id=?1 order by score desc",nativeQuery = true)
        List<ContestLeaderboard> findAllByOrderByScoreDesc(Integer contestId);
}
