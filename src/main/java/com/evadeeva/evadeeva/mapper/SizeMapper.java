package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.Size;
import com.evadeeva.evadeeva.web.dto.request.SizeRequest;
import com.evadeeva.evadeeva.web.dto.response.SizeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(uses = ProductMapper.class)
public interface SizeMapper {
    SizeResponse mapModelToResponse(Size size);

    @Mapping(target = "color.id", source = "colorId")
    Size mapRequestedToModel(SizeRequest sizeRequest);

}
