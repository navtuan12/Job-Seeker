package com.navtuan12.job_seeker_server.repository;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.navtuan12.job_seeker_server.dto.response.JobResponse;
import com.navtuan12.job_seeker_server.models.Job;

@Repository
public interface JobRepository extends MongoRepository<Job, ObjectId>{
    // @Query("{$or : [{'jobTitle': { $regex: ?0, $options:'i' }}")
    // List<Job> findJobByRegexString(final String regexString);

    Optional<Job> findById(ObjectId id);
    Optional<List<Job>> findByCompany(String company);
}
