package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.Address;
import com.evadeeva.evadeeva.models.User;
import com.evadeeva.evadeeva.web.dto.request.AddressRequest;
import com.evadeeva.evadeeva.web.dto.request.UserRequest;
import com.evadeeva.evadeeva.web.dto.response.AddressResponse;
import org.mapstruct.*;

@Mapper
public interface AddressMapper {
    AddressResponse mapToResponse(Address address);

    @Mapping(target = "user.id", source = "userId")
    Address mapToModel(AddressRequest addressRequest);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(@MappingTarget Address address, AddressRequest addressRequest);
}
