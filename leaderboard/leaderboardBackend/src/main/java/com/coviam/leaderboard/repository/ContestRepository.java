package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.Contest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends CrudRepository<Contest,Integer> {

}
