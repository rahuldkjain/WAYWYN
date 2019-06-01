package com.waywyn.userresponse.repository;

import com.waywyn.userresponse.entity.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterRepository extends MongoRepository<Counter, String> {
}
