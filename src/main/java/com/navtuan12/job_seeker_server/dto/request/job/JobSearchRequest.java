package com.navtuan12.job_seeker_server.dto.request.job;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobSearchRequest {
    private String search;
    private String location;
    private String sort;
    private String jtype;
    private String exp;
    private int pageNum = 1;
    private int limit = 20;
}
