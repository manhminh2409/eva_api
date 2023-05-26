package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {
	List<Banner> findByCategoryId(long categoryId);
	
	List<Banner> findByStatus(int status);
}
