package com.navtuan12.job_seeker_server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.navtuan12.job_seeker_server.dto.request.company.CompanyLoginRequest;
import com.navtuan12.job_seeker_server.dto.request.company.CompanyRegisterRequest;
import com.navtuan12.job_seeker_server.dto.response.ApiResponse;
import com.navtuan12.job_seeker_server.dto.response.CompanyProfileResponse;
import com.navtuan12.job_seeker_server.dto.response.CompanyRegisterResponse;
import com.navtuan12.job_seeker_server.services.CompanyService;
import com.navtuan12.job_seeker_server.utils.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {
    
    CompanyService companyService;
    JwtService jwtService;

    @PostMapping("/register")
    public ApiResponse<CompanyRegisterResponse> register(@RequestBody CompanyRegisterRequest request) {
        ApiResponse<CompanyRegisterResponse> response = new ApiResponse<CompanyRegisterResponse>();
        response.setSuccess(true);
        response.setMessage("Company Account Created Successfully");
        response.setResult(companyService.register(request));
        return response;
    }

    @PostMapping("/login")
    public ApiResponse<CompanyProfileResponse> login(@RequestBody CompanyLoginRequest request) {
        ApiResponse<CompanyProfileResponse> response = new ApiResponse<CompanyProfileResponse>();
        response.setSuccess(true);
        response.setMessage("Login Successfully");
        response.setResult(companyService.login(request));
        response.addAdditionalProperty("token", jwtService.generatorToken(request.getEmail()));
        return response;
    }

    @GetMapping("/get-company-profile")
    public ApiResponse<CompanyProfileResponse> getMethodName1(HttpServletRequest request) {
        String token = jwtService.getTokenFromRequest(request);
        String email = jwtService.getPayloadFromToken(token);
        ApiResponse<CompanyProfileResponse> response = new ApiResponse<CompanyProfileResponse>();
        response.setSuccess(true);
        response.setResult(companyService.getCompanyProfileByEmail(email));
        return response;
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
