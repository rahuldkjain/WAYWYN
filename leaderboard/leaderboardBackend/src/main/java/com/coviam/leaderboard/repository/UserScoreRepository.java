package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.UserScore;
import com.coviam.leaderboard.pkclasses.UserScorePK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserScoreRepository extends CrudRepository<UserScore,UserScorePK> {
}
