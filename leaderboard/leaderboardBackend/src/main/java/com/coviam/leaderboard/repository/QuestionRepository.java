package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question,Integer> {

}
