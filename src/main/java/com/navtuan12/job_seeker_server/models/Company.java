package com.navtuan12.job_seeker_server.models;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    private ObjectId id;

    private String name;
    private String email;
    private String password;
    private String contact;
    private String location;
    private String about;
    private List<ObjectId> jobPosts;
    private String verified;
}
