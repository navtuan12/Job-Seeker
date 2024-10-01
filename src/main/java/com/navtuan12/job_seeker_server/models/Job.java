package com.navtuan12.job_seeker_server.models;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    private ObjectId id;

    private String companyId;
    private String jobTitle;
    private String jobType;
    private String location;
    private String salary;
    private int vacancies;
    private int experience;
    private List<String> detail;
    private List<ObjectId> applicants;
}
