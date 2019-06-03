package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.MonthlyLeaderboard;
import com.coviam.leaderboard.pkclasses.MonthlyLeaderboardPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyLeaderboardRepository extends CrudRepository<MonthlyLeaderboard,MonthlyLeaderboardPK> {

    @Query(value = "select * from monthly_leaderboard where month_id = ?1 order by user_rank asc", nativeQuery = true)
    List<MonthlyLeaderboard> findAllByOrderByUserRankAsc(long monthId);

    @Query(value = "select distinct month_id from monthly_leaderboard",nativeQuery = true)
    List<Integer> findDistinctMonthId();
}
