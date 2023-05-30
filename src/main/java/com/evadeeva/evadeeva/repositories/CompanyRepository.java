package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByIdAndStatus(long companyId, int status);
}
