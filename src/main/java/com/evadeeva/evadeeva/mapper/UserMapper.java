package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.User;
import com.evadeeva.evadeeva.web.dto.request.RegisterRequest;
import com.evadeeva.evadeeva.web.dto.request.UserRequest;
import com.evadeeva.evadeeva.web.dto.response.UserResponse;
import org.mapstruct.*;

@Mapper(uses = AddressMapper.class)
public interface UserMapper {
    /**
     *
     */
    User mapSignupToModel(RegisterRequest registerRequest);

    /**
     *
     */
    @Mapping(target = "roles",source = "roles")
    @Mapping(target = "addresses", source = "addresses")
    UserResponse mapModelToResponse(User user);

    //User mapRequestToModel(UserRequest userRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(@MappingTarget User user, UserRequest userRequest);
}
