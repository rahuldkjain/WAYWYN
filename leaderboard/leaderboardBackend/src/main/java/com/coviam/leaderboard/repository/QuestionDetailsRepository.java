package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.QuestionDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDetailsRepository extends CrudRepository<QuestionDetails,Integer> {

}

