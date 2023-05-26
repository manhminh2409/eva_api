package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.Banner;
import com.evadeeva.evadeeva.web.dto.request.BannerRequest;
import com.evadeeva.evadeeva.web.dto.response.BannerResponse;
import org.mapstruct.Mapper;

@Mapper
public interface BannerMapper {
	Banner mapBannerToModel(BannerRequest bannerRequest);
	
	BannerResponse mapModelToResponse(Banner banner);
}
