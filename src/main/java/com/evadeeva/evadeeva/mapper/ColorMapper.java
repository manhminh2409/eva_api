package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.Color;
import com.evadeeva.evadeeva.web.dto.request.ColorRequest;
import com.evadeeva.evadeeva.web.dto.response.ColorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ColorMapper {

    @Mapping(target = "sizes", source = "sizes")
    ColorResponse mapModelToResponse(Color color);

    @Mapping(target = "product.id", source = "productId")
    Color mapRequestedToModel(ColorRequest colorRequest);

}
