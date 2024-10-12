package com.navtuan12.job_seeker_server.models;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
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

    private String company;
    private String jobTitle;
    private String jobType;
    private String location;
    private int salary;
    private int vacancies;
    private int experience;
    private Detail detail;
    private List<ObjectId> application;

    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
}
