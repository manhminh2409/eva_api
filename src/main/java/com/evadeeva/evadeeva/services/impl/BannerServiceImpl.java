package com.evadeeva.evadeeva.services.impl;

import com.evadeeva.evadeeva.mapper.BannerMapper;
import com.evadeeva.evadeeva.models.Banner;
import com.evadeeva.evadeeva.models.Category;
import com.evadeeva.evadeeva.repositories.BannerRepository;
import com.evadeeva.evadeeva.repositories.CategoryRepository;
import com.evadeeva.evadeeva.services.BannerService;
import com.evadeeva.evadeeva.web.dto.response.BannerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.evadeeva.evadeeva.config.Constants.ACTIVE_STATUS;

@Service
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final CategoryRepository categoryRepository;
    private final BannerMapper bannerMapper;

    public BannerServiceImpl(BannerRepository bannerRepository, CategoryRepository categoryRepository, BannerMapper bannerMapper) {
        this.bannerRepository = bannerRepository;
        this.categoryRepository = categoryRepository;
        this.bannerMapper = bannerMapper;
    }

    @Override
    public List<BannerResponse> getNumberOfBanners(int number) {
        Pageable pageable = PageRequest.of(0, number, Sort.by("id").descending());
        Page<Banner> banners = bannerRepository.findByStatus(pageable, ACTIVE_STATUS);
        if (!banners.isEmpty()){
            return banners.getContent().stream()
                    .map(bannerMapper::mapModelToResponse)
                    .toList();
        }
        return null;
    }

    @Override
    public List<BannerResponse> getNumberOfBannersByCategory(long categoryId, int number) {
        Pageable pageable = PageRequest.of(0, number, Sort.by("id").descending());

        Category category = categoryRepository.findById(categoryId).orElseThrow();

        Page<Banner> banners = bannerRepository.findByCategoryAndStatus(pageable, category, ACTIVE_STATUS);
        if (!banners.isEmpty()){
            return banners.getContent().stream()
                    .map(bannerMapper::mapModelToResponse)
                    .toList();
        }
        return null;
    }
}
