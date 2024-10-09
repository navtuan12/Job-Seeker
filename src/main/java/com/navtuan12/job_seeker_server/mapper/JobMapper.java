package com.navtuan12.job_seeker_server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.navtuan12.job_seeker_server.dto.request.job.JobUpdateRequest;
import com.navtuan12.job_seeker_server.dto.request.job.JobUploadRequest;
import com.navtuan12.job_seeker_server.dto.response.CompanyProfileResponse;
import com.navtuan12.job_seeker_server.dto.response.JobResponse;
import com.navtuan12.job_seeker_server.dto.response.JobSearchResponse;
import com.navtuan12.job_seeker_server.models.Job;

@Mapper(componentModel = "spring")
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface JobMapper {

    @Mappings({
        @Mapping(source = "companyProfile", target = "company"),
        @Mapping(source = "job.id", target = "id")
    })
    JobSearchResponse toJobSearchResponse(Job job, CompanyProfileResponse companyProfile);

    Job toJob(JobUploadRequest request);

    JobResponse toJobResponse(Job job);

    @Mappings({
        @Mapping(source = "desc", target = "detail.desc"),
        @Mapping(source = "requirements", target = "detail.requirements")
    })
    void updateJob(@MappingTarget Job job, JobUpdateRequest request);
}
