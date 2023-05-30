package com.evadeeva.evadeeva.services;

import com.evadeeva.evadeeva.models.Banner;
import com.evadeeva.evadeeva.web.dto.response.BannerResponse;

import java.util.List;

public interface BannerService {
    /**
     * get number of banners
     */
    List<BannerResponse> getNumberOfBanners(int number);

    List<BannerResponse> getNumberOfBannersByCategory(long categoryId, int number);
}
