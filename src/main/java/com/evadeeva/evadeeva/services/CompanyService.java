package com.evadeeva.evadeeva.services;

import com.evadeeva.evadeeva.repositories.CompanyRepository;
import com.evadeeva.evadeeva.web.dto.request.CompanyRequest;
import com.evadeeva.evadeeva.web.dto.response.CompanyResponse;

public interface CompanyService {
    CompanyResponse getCompany(long companyId);
    CompanyResponse createCompanyInfo(CompanyRequest companyRequest);
    CompanyResponse updateCompanyInfo(long companyId, CompanyRequest companyRequest);
}
