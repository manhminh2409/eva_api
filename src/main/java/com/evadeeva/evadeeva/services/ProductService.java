package com.evadeeva.evadeeva.services;

import com.evadeeva.evadeeva.models.Product;
import com.evadeeva.evadeeva.web.dto.request.ProductRequest;
import com.evadeeva.evadeeva.web.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    /**
     *
     */
    List<ProductResponse> getProducts(int pageNo, int pageSize, String sortBy);
    ProductResponse getProduct(long productId);
    ProductResponse getProductBySize(long sizeId);
    List<ProductResponse> getProductsByCategory(long categoryId, int pageNo, int pageSize, String sortBy);
    ProductResponse createProduct(ProductRequest productRequest);

}
