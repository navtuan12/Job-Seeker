package com.navtuan12.job_seeker_server.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.navtuan12.job_seeker_server.models.Job;

@Repository
public interface JobRepository extends MongoRepository<Job, ObjectId>{
    
}
