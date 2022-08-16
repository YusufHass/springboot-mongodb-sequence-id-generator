package com.springboot.springbootmongodbsequenceidgenerator.service;


import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.springboot.springbootmongodbsequenceidgenerator.entity.DbSequence;

@Service
public class SequenceGenerator {
    @Autowired
    private MongoOperations mongoOperations;
    public int getSequenceNumber(String sequenceName){
        //get sequence number
        Query query= new Query(Criteria.where("id").is(sequenceName));
        //modify or update the sequence
        Update update= new Update().inc("seq", 1);

        //modify in the document. Here we specify the query then will update inside 
        // the DbSequnce 
        DbSequence counter=mongoOperations.findAndModify(query, update,
         FindAndModifyOptions.options().returnNew(true).
         upsert(true), DbSequence.class);
         //the ternary operator and return the initial id or the updated one from the getSeqNo
         return !Objects.isNull(counter)? counter.getSeq():1;



    }

    
}
