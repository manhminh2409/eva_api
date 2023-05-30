package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.ProductImage;
import com.evadeeva.evadeeva.web.dto.request.ProductImageRequest;
import com.evadeeva.evadeeva.web.dto.response.ProductImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
public interface ProductImageMapper {
	ProductImageResponse mapModelToResponse(ProductImage productImage);
}
