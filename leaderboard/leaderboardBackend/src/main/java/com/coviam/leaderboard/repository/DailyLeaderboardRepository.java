package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.DailyLeaderboard;
import com.coviam.leaderboard.pkclasses.DailyLeaderboardPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyLeaderboardRepository extends CrudRepository<DailyLeaderboard,DailyLeaderboardPK> {

    @Query(value = "select username,sum(score) as score from daily_leaderboard where day_id between ?1 and ?2 group by username order by sum(score) desc;",nativeQuery = true)
    List<Object> findByUserIdGroupByDateRange(long startdate, long today);
}
