package com.navtuan12.job_seeker_server.services;

import java.util.List;
import org.bson.types.ObjectId;
import com.navtuan12.job_seeker_server.dto.request.company.CompanyLoginRequest;
import com.navtuan12.job_seeker_server.dto.request.company.CompanyRegisterRequest;
import com.navtuan12.job_seeker_server.dto.response.CompanyIdResponse;
import com.navtuan12.job_seeker_server.dto.response.CompanyProfileResponse;
import com.navtuan12.job_seeker_server.dto.response.CompanyRegisterResponse;
import com.navtuan12.job_seeker_server.dto.response.JobResponse;

public interface CompanyService {
    CompanyRegisterResponse register(CompanyRegisterRequest request);
    CompanyProfileResponse login(CompanyLoginRequest request);
    CompanyProfileResponse getCompanyProfileById(ObjectId companyId);
    CompanyProfileResponse getCompanyProfileByEmail(String email);
    CompanyIdResponse getCompanyId(ObjectId companyId, String companyEmail);
    List<JobResponse> getJobsByCompanyId(ObjectId companyId);
}
