package com.navtuan12.job_seeker_server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.navtuan12.job_seeker_server.dto.request.CompanyLoginRequest;
import com.navtuan12.job_seeker_server.dto.request.CompanyRegisterRequest;
import com.navtuan12.job_seeker_server.models.Company;
import com.navtuan12.job_seeker_server.services.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    
    private CompanyService companyService;
    
    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/register")
    public Company register(@RequestBody CompanyRegisterRequest request) {
        return companyService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody CompanyLoginRequest request) {
        return companyService.login(request);
    }

}
