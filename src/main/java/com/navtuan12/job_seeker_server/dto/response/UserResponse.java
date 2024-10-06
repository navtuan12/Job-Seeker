package com.navtuan12.job_seeker_server.dto.response;

import org.bson.types.ObjectId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String location;
    private String accountType;
    private String jobTitle;
    private String about;
}
