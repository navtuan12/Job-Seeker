package com.navtuan12.job_seeker_server.dto.request.user;

import java.util.Date;
import org.bson.types.ObjectId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String contact;
    private String location;
    private String jobTitle;
    private String email;
    private String about;
    private Date createdAt;
    private Date updatedAt;
    private String token;
    private ObjectId id;
    private String accountType;
}
