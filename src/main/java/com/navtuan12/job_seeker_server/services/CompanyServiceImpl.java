package com.navtuan12.job_seeker_server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.navtuan12.job_seeker_server.dto.request.CompanyLoginRequest;
import com.navtuan12.job_seeker_server.dto.request.CompanyRegisterRequest;
import com.navtuan12.job_seeker_server.models.Company;
import com.navtuan12.job_seeker_server.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService{
    
    private CompanyRepository companyRepository;
    
    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company register(CompanyRegisterRequest request) {
        Company company = new Company();

        company.setName(request.getName());
        company.setEmail(request.getEmail());
        company.setPassword(request.getPassword());

        return companyRepository.save(company);
    }

    @Override
    public String login(CompanyLoginRequest request) {
        Company company = companyRepository.findByEmail(request.getEmail());
        if(company.getEmail() != request.getEmail()){
            return "wrong email or password!";
        }

        if(company.getPassword() != request.getPassword()){
            return "wrong password!";
        }

        return "Login successful!";
    }
}
