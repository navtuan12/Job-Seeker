package com.navtuan12.job_seeker_server.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.navtuan12.job_seeker_server.dto.request.job.JobSearchRequest;
import com.navtuan12.job_seeker_server.dto.request.job.JobUploadRequest;
import com.navtuan12.job_seeker_server.dto.response.ApiResponse;
import com.navtuan12.job_seeker_server.dto.response.JobResponse;
import com.navtuan12.job_seeker_server.dto.response.JobSearchResponse;
import com.navtuan12.job_seeker_server.services.JobService;
import com.navtuan12.job_seeker_server.utils.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JobsController {

    JobService jobService;
    JwtService jwtService;

    @PostMapping("/upload-job")
    public ApiResponse<JobResponse> postMethodName(@RequestBody JobUploadRequest request,
            HttpServletRequest httpRequest) {
        String token = jwtService.getTokenFromRequest(httpRequest);
        String companyEmail = jwtService.getPayloadFromToken(token);
        ApiResponse<JobResponse> response = new ApiResponse<JobResponse>();
        response.setSuccess(true);
        response.setMessage("Job Posted Successfully");
        response.setResult(jobService.uploadJob(request, companyEmail));
        return response;
    }

    @PutMapping("update-job/{jobId}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        // TODO: process PUT request

        return entity;
    }

    @GetMapping("/find-jobs")
    public ApiResponse<List<JobSearchResponse>> jobSearch(
            @ModelAttribute JobSearchRequest request) {
        List<JobSearchResponse> jobSearchResponses = jobService.jobSearch(request);
        int totalJobs = jobSearchResponses.size();
        int limit = request.getLimit();
        int numOfPages = (int) Math.ceil((double) totalJobs / limit);
        ApiResponse<List<JobSearchResponse>> response = new ApiResponse<List<JobSearchResponse>>();

        response.setSuccess(true);
        response.addAdditionalProperty("totalJobs", totalJobs);
        response.setResult(jobSearchResponses);
        response.addAdditionalProperty("page", request.getPageNum());
        response.addAdditionalProperty("numOfPage", numOfPages);

        return response;
    }

    @GetMapping("/get-job-detail/{jobId}")
    public String getMethodName1(@RequestParam String param) {
        return new String();
    }
}
