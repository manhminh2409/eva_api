package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	List<Category> findByStatus(int status);

	Category findByStatusAndId(int status, long id);

}
