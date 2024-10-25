package com.example.booking_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.booking_app.dto.request.RoleRequest;
import com.example.booking_app.dto.response.RoleResponse;
import com.example.booking_app.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
