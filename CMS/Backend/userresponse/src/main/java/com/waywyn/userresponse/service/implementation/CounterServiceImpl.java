package com.waywyn.userresponse.service.implementation;

import com.waywyn.userresponse.entity.Counter;
import com.waywyn.userresponse.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


import java.util.logging.Logger;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private MongoOperations mongoOperations;

    private final static Logger LOGGER =
            Logger.getLogger(CounterServiceImpl.class.getName());

    @Override
    public int genNextSequence(String seqName) {
        Counter counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                Counter.class);
        return counter.getSeq();
    }
}
