package com.navtuan12.job_seeker_server.mapper;

import org.mapstruct.Mapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.navtuan12.job_seeker_server.dto.request.company.CompanyRegisterRequest;
import com.navtuan12.job_seeker_server.dto.response.CompanyProfileResponse;
import com.navtuan12.job_seeker_server.dto.response.CompanyRegisterResponse;
import com.navtuan12.job_seeker_server.models.Company;

@Mapper(componentModel = "spring")
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface CompanyMapper {
    CompanyProfileResponse toCompanyProfileResponse(Company company);
    CompanyRegisterResponse toCompanyRegisterResponse(Company company);
    Company toCompany(CompanyRegisterRequest request);
}
