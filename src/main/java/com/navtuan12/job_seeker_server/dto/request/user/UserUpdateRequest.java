package com.navtuan12.job_seeker_server.dto.request.user;

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
    private String about;
}
