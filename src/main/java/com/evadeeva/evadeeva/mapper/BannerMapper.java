package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.Banner;
import com.evadeeva.evadeeva.web.dto.response.BannerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BannerMapper {
	@Mapping(target = "categoryId", source = "category.id")
	BannerResponse mapModelToResponse(Banner banner);
}
