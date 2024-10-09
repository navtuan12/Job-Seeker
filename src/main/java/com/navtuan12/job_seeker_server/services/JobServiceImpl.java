package com.navtuan12.job_seeker_server.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.navtuan12.job_seeker_server.dto.request.job.JobSearchRequest;
import com.navtuan12.job_seeker_server.dto.request.job.JobUpdateRequest;
import com.navtuan12.job_seeker_server.dto.request.job.JobUploadRequest;
import com.navtuan12.job_seeker_server.dto.response.CompanyProfileResponse;
import com.navtuan12.job_seeker_server.dto.response.JobResponse;
import com.navtuan12.job_seeker_server.dto.response.JobSearchResponse;
import com.navtuan12.job_seeker_server.exception.AppException;
import com.navtuan12.job_seeker_server.exception.ErrorCode;
import com.navtuan12.job_seeker_server.mapper.JobMapper;
import com.navtuan12.job_seeker_server.models.Company;
import com.navtuan12.job_seeker_server.models.Detail;
import com.navtuan12.job_seeker_server.models.Job;
import com.navtuan12.job_seeker_server.repository.CompanyRepository;
import com.navtuan12.job_seeker_server.repository.JobRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;
    MongoTemplate mongoTemplate;
    JobMapper jobMapper;
    CompanyService companyService;
    CompanyRepository companyRepository;

    @Override
    public List<JobSearchResponse> jobSearch(JobSearchRequest request) {
        String location = request.getLocation();
        String search = request.getSearch();
        String sort = request.getSort();
        String jtype = request.getJtype();
        String exp = request.getExp();

        Query query = new Query();

        // Location filtering
        if (location != null && !location.isEmpty()) {
            query.addCriteria(Criteria.where("location").regex(location, "i"));
        }

        // Job type filtering
        if (jtype != null && !jtype.isEmpty()) {
            List<String> jobTypes = List.of(jtype.split(","));
            query.addCriteria(Criteria.where("jobType").in(jobTypes));
        }

        // Experience filtering
        if (exp != null && !exp.isEmpty()) {
            int[] experienceRange = parseExperienceRange(exp);
            query.addCriteria(
                    Criteria.where("experience").gte(experienceRange[0]).lte(experienceRange[1]));
        }

        // Search filtering
        if (search != null && !search.isEmpty()) {
            query.addCriteria(
                    new Criteria().orOperator(Criteria.where("jobTitle").regex(search, "i")));
        }

        // Sorting
        if (sort != null) {
            switch (sort) {
                case "Newest":
                    query.with(Sort.by(Order.desc("createdAt")));
                    break;
                case "Oldest":
                    query.with(Sort.by(Order.asc("createdAt")));
                    break;
                case "A-Z":
                    query.with(Sort.by(Order.asc("jobTitle")));
                    break;
                case "Z-A":
                    query.with(Sort.by(Order.desc("jobTitle")));
                    break;
            }
        }

        // Pagination
        Pageable pageable = PageRequest.of(request.getPageNum() - 1, request.getLimit());
        query.with(pageable);

        // Execute query
        List<Job> jobs = mongoTemplate.find(query, Job.class);
        return jobs.stream().map(job -> {
            CompanyProfileResponse companyProfile =
                    companyService.getCompanyProfileById(new ObjectId(job.getCompany()));
            
            return jobMapper.toJobSearchResponse(job, companyProfile);
        }).collect(Collectors.toList());
    }

    private int[] parseExperienceRange(String exp) {
        String[] expParts = exp.split("-");
        int expStart = Integer.parseInt(expParts[0]) - 1;
        int expEnd = Integer.parseInt(expParts[1]) + 1;
        return new int[] {expStart, expEnd};
    }

    @Override
    public JobResponse uploadJob(JobUploadRequest request, String companyEmail) {
        Job job = jobMapper.toJob(request);
        Date now = new Date();
        job.setCreatedAt(now);
        job.setUpdatedAt(now);

        Company company = companyRepository.findByEmail(companyEmail)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_TOKEN));

        job.setCompany(company.getId().toString());
        Detail detail = new Detail(request.getDesc(), request.getRequirements());
        job.setDetail(detail);
        Job saveJob = jobRepository.save(job);
        if (company.getJobPosts() == null) {
            List<ObjectId> jobs = new ArrayList<ObjectId>();
            company.setJobPosts(jobs);
        }
        company.getJobPosts().add(saveJob.getId());
        companyRepository.save(company);
        return jobMapper.toJobResponse(saveJob);
    }

    @Override
    public JobSearchResponse getJobDetail(ObjectId jobId) {
        Job job = jobRepository.findById(jobId)
                    .orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_FOUND));
        
        CompanyProfileResponse response = companyService.getCompanyProfileById(new ObjectId(job.getCompany()));
        return jobMapper.toJobSearchResponse(job, response);
    }

    @Override
    public List<JobSearchResponse> getSimilarJobs(JobSearchResponse job) {
        Query query = new Query();
        query.addCriteria(new Criteria().orOperator(
            Criteria.where("jobTitle").regex(job.getJobTitle(), "i")
        ));
        query.limit(6);
        query.with(Sort.by(Sort.Order.desc("_id")));

        List<Job> similarJobs = mongoTemplate.find(query, Job.class);
        return similarJobs.stream()
                .map(j -> jobMapper.toJobSearchResponse(j, companyService.getCompanyProfileById(new ObjectId(j.getCompany()))))
                .collect(Collectors.toList());
    }

    @Override
    public JobResponse updateJob(ObjectId id, JobUpdateRequest request) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_FOUND));

        jobMapper.updateJob(job, request);
        jobRepository.save(job);        
        return jobMapper.toJobResponse(job);
    }
}
