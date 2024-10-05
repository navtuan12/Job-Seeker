package com.navtuan12.job_seeker_server.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.navtuan12.job_seeker_server.dto.request.CompanyLoginRequest;
import com.navtuan12.job_seeker_server.dto.request.CompanyRegisterRequest;
import com.navtuan12.job_seeker_server.models.Company;
import com.navtuan12.job_seeker_server.services.CompanyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {
    
    CompanyService companyService;

    @PostMapping("/register")
    public Company register(@RequestBody CompanyRegisterRequest request) {
        return companyService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody CompanyLoginRequest request) {
        return companyService.login(request);
    }

    @GetMapping("/get-company-profile")
    public String getMethodName1(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("/get-company-joblist")
    public String getMethodName2(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/get-company")
    public String getMethodName3(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/get-company/{companyId}")
    public String getMethodName4(@PathVariable String comnapyId) {
        return new String();
    }
    
    
    
}
