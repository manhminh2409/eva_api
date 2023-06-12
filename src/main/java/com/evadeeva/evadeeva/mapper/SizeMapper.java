package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.Size;
import com.evadeeva.evadeeva.web.dto.request.SizeRequest;
import com.evadeeva.evadeeva.web.dto.response.SizeResponse;
import org.mapstruct.Mapper;

@Mapper(uses = ProductMapper.class)
public interface SizeMapper {
    SizeResponse mapModelToResponse(Size size);
    Size mapRequestedToModel(SizeRequest sizeRequest);

}
