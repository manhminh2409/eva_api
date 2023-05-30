package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.Banner;
import com.evadeeva.evadeeva.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {
	Page<Banner> findByCategoryAndStatus(Pageable pageable, Category category, int status);
	Page<Banner> findByStatus(Pageable pageable, int status);

}
