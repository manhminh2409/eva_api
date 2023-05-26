package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.Role;
import com.evadeeva.evadeeva.web.dto.response.RoleResponse;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
    RoleResponse mapModelToResponse(Role role);
}
