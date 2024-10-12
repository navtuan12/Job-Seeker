package com.navtuan12.job_seeker_server.dto.request.job;

import java.util.List;
import com.navtuan12.job_seeker_server.models.Detail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobUploadRequest {
    private String jobTitle;
    private String jobType;
    private String location;
    private int salary;
    private int vacancies;
    private int experience;
    private String desc;
    private String requirements;
}