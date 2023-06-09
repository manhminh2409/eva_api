package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.Category;
import com.evadeeva.evadeeva.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByStatus(Pageable pageable, int status);

    Product findByIdAndStatus(long productId, int status);

    Page<Product> findAllByProductCategoryAndStatus(Pageable pageable, Category category, int status);

    @Query("select max(p.id) from Product p")
    Long findNewestId();
}
