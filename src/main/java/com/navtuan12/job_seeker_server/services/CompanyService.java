package com.navtuan12.job_seeker_server.services;

import com.navtuan12.job_seeker_server.dto.request.CompanyLoginRequest;
import com.navtuan12.job_seeker_server.dto.request.CompanyRegisterRequest;
import com.navtuan12.job_seeker_server.models.Company;

public interface CompanyService {
    Company register(CompanyRegisterRequest request);
    String login(CompanyLoginRequest request);
}
