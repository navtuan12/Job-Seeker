package com.navtuan12.job_seeker_server.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.navtuan12.job_seeker_server.models.Company;

public interface CompanyRepository extends MongoRepository <Company, ObjectId> {
    
}