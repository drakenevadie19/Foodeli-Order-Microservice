package com.codedecode.order.service;

import com.codedecode.order.entity.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceGenerator {
    // Class to generate OrderId

    @Autowired
    private MongoOperations mongoOperations;

    // This integer will be ID for next order getting generated
    public int generateNextOrderId() {

        // Previous generated ID (lastly used ID) will be saved in Document
        // Here is to getch the sequence from the sequence document (from "sequence" collection saved in MongoDB) and update it
        // If the sequence document is not available, then initialize that sequence with 1 and update and insert that document in Document in Mongo
        Sequence counter = mongoOperations.findAndModify(
            query(where("_id").is("sequence")),
            new Update().inc("sequence", 1),
            options().returnNew(true).upsert(true),
            Sequence.class);

        return counter.getSequence();

    }

}
