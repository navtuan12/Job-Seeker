package com.navtuan12.job_seeker_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyLoginRequest {
    private String email;
    private String password;
}
