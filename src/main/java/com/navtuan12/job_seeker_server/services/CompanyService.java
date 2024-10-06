package com.navtuan12.job_seeker_server.services;

import com.navtuan12.job_seeker_server.dto.request.company.CompanyLoginRequest;
import com.navtuan12.job_seeker_server.dto.request.company.CompanyRegisterRequest;
import com.navtuan12.job_seeker_server.models.Company;

public interface CompanyService {
    Company register(CompanyRegisterRequest request);
    String login(CompanyLoginRequest request);
}
