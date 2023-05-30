package com.evadeeva.evadeeva.services.impl;

import com.evadeeva.evadeeva.mapper.CompanyMapper;
import com.evadeeva.evadeeva.mapper.SocialMediaMapper;
import com.evadeeva.evadeeva.models.Company;
import com.evadeeva.evadeeva.models.SocialMedia;
import com.evadeeva.evadeeva.models.dtos.SocialMediaDto;
import com.evadeeva.evadeeva.repositories.CompanyRepository;
import com.evadeeva.evadeeva.repositories.SocialMediaRepository;
import com.evadeeva.evadeeva.services.CompanyService;
import com.evadeeva.evadeeva.web.dto.request.CompanyRequest;
import com.evadeeva.evadeeva.web.dto.response.CompanyResponse;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.evadeeva.evadeeva.config.Constants.ACTIVE_STATUS;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final SocialMediaRepository socialMediaRepository;
    private final CompanyMapper companyMapper;
    private final SocialMediaMapper socialMediaMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, SocialMediaRepository socialMediaRepository, CompanyMapper companyMapper, SocialMediaMapper socialMediaMapper) {
        this.companyRepository = companyRepository;
        this.socialMediaRepository = socialMediaRepository;
        this.companyMapper = companyMapper;
        this.socialMediaMapper = socialMediaMapper;
    }

    @Override
    public CompanyResponse getCompany(long companyId) {
        Company company = companyRepository.findByIdAndStatus(companyId, ACTIVE_STATUS);
        if (company != null){
            return companyMapper.mapToResponse(company);
        }
        return null;
    }

    @Override
    public CompanyResponse createCompanyInfo(CompanyRequest companyRequest) {
        Company company = companyMapper.mapToModel(companyRequest);
        Date currentDate = new Date();
        company.setCreatedDate(currentDate);
        company.setModifiedDate(currentDate);
        company.setStatus(1);
        Company companyNew = companyRepository.save(company);
        for (SocialMediaDto s: companyRequest.getSocialMedias()){
            SocialMedia socialMedia = socialMediaMapper.mapToModel(s);
            socialMedia.setCompany(companyNew);
            socialMediaRepository.save(socialMedia);
        }
        return companyMapper.mapToResponse(companyNew);
    }

    @Override
    public CompanyResponse updateCompanyInfo(long companyId, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(companyId).orElseThrow();
        companyMapper.updateModel(company, companyRequest);
        Date currentDate = new Date();
        company.setModifiedDate(currentDate);
        companyRepository.save(company);
        return companyMapper.mapToResponse(company);
    }
}
