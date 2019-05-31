package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.Contest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ContestRepository extends CrudRepository<Contest,Integer> {

    @Query(value = "select * from contest where date >= ?1", nativeQuery = true)
    List<Contest> findActiveContest(Date dateVal);
}
