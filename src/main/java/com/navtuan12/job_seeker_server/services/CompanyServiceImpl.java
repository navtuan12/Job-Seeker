package com.navtuan12.job_seeker_server.services;

import org.bson.types.ObjectId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.navtuan12.job_seeker_server.dto.request.company.CompanyLoginRequest;
import com.navtuan12.job_seeker_server.dto.request.company.CompanyRegisterRequest;
import com.navtuan12.job_seeker_server.dto.response.CompanyProfileResponse;
import com.navtuan12.job_seeker_server.dto.response.CompanyRegisterResponse;
import com.navtuan12.job_seeker_server.exception.AppException;
import com.navtuan12.job_seeker_server.exception.ErrorCode;
import com.navtuan12.job_seeker_server.mapper.CompanyMapper;
import com.navtuan12.job_seeker_server.models.Company;
import com.navtuan12.job_seeker_server.models.User;
import com.navtuan12.job_seeker_server.repository.CompanyRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyServiceImpl implements CompanyService{
    
    CompanyRepository companyRepository;
    CompanyMapper companyMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public CompanyRegisterResponse register(CompanyRegisterRequest request) {
        if(companyRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        Company company = companyMapper.toCompany(request);
        company.setPassword(passwordEncoder.encode(request.getPassword()));

        return companyMapper.toCompanyRegisterResponse(companyRepository.save(company));
    }

     @Override
    public CompanyProfileResponse login(CompanyLoginRequest request) {
        Company company = companyRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        var check = passwordEncoder.matches(request.getPassword(), company.getPassword());
        if(!check) {
            throw new AppException(ErrorCode.WRONG_PASSWORD);
        }
        return companyMapper.toCompanyProfileResponse(company);
    }

    @Override
    public CompanyProfileResponse getCompanyProfileById(ObjectId companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        return companyMapper.toCompanyProfileResponse(company);
    }

    @Override
    public CompanyProfileResponse getCompanyProfileByEmail(String email) {
        Company company = companyRepository.findByEmail(email)
                                            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return companyMapper.toCompanyProfileResponse(company);
    }
}
