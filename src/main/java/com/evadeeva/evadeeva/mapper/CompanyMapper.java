package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.Company;
import com.evadeeva.evadeeva.web.dto.request.CompanyRequest;
import com.evadeeva.evadeeva.web.dto.response.CompanyResponse;
import org.mapstruct.*;

@Mapper(uses = SocialMediaMapper.class)
public interface CompanyMapper {

    @Mapping(target = "socials", source = "socialMedias")
    CompanyResponse mapToResponse(Company company);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "socialMedias", source = "socialMedias")
    Company mapToModel(CompanyRequest companyRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(@MappingTarget Company company, CompanyRequest companyRequest);
}
