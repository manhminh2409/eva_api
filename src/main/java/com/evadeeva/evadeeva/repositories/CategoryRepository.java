package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	List<Category> findByStatus(int status);
	List<Category> findAllByStatusAndType(int status, int type);
	Category findByStatusAndIdAndType(int status, long id, int type);

}
