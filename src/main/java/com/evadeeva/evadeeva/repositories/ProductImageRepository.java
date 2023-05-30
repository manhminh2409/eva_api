package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.Product;
import com.evadeeva.evadeeva.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByProduct(Product product);
}
