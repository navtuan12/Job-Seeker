package com.navtuan12.job_seeker_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String contact;
    private String location;
    private String jobTitle;
    private String about;
}
