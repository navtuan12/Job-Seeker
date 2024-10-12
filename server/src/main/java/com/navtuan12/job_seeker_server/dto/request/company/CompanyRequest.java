package com.navtuan12.job_seeker_server.dto.request.company;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyRequest {
    private String search;
    private String sort;
    private String location;
    private int pageNum = 1;
    private int limit = 20;
}
