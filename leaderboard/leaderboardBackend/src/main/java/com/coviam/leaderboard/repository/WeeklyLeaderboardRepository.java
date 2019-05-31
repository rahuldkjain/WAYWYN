package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.WeeklyLeaderboard;
import com.coviam.leaderboard.pkclasses.WeeklyLeaderboardPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeeklyLeaderboardRepository extends CrudRepository<WeeklyLeaderboard,WeeklyLeaderboardPK> {

    @Query(value = "select username,sum(score) as score from weekly_leaderboard where week_id between ?1 and ?2 group by username order by sum(score) desc;",nativeQuery = true)
    List<Object> findByUserIdGroupByDateRange(long startdate, long today);

    @Query(value = "select * from weekly_leaderboard where week_id = ?1 order by user_rank asc", nativeQuery = true)
    List<WeeklyLeaderboard> findAllByOrderByUserRankAsc(long weekId);
}
