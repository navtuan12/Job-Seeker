package com.navtuan12.job_seeker_server.models;

import java.io.Serializable;
import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    @Id
    private ObjectId id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String contact;
    private String location;
    private String accountType = "seeker";
    private String profileUrl;
    private String cvUrl;
    private String jobTitle;
    private String about;
    private Boolean verified;
    private Date createdAt;
    private Date updatedAt;
}