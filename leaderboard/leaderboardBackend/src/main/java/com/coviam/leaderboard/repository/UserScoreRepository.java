package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.UserScore;
import com.coviam.leaderboard.pkclasses.UserScorePK;
import com.coviam.leaderboard.queryresult.UserAggregateScore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserScoreRepository extends CrudRepository<UserScore,UserScorePK> {

    List<UserScore> findAllByOrderByScoreDesc();


    List<UserScore> findAllByUserEndDate(Date dateVal);
    @Query(value = "select user_id,username,sum(score) as score from user_score us where us.user_end_date=?1 group by user_id,username order by sum(score) desc;",nativeQuery = true)
    List<Object> findScoreSumGroupByUserId(Date dateVal);

    @Query(value = "select * from user_score where contest_id = ?1", nativeQuery = true)
    List<UserScore> findByContestId(Integer contestId);
}
