package com.coviam.leaderboard.repository;

import com.coviam.leaderboard.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question,Integer> {

    @Query(value = "select * from question where correct_count = (select max(correct_count) from question)", nativeQuery = true)
    List<Question> findMaxCountQuestions();
}
