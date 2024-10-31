package com.example.booking_app.mapper;

import org.mapstruct.Mapper;

import com.example.booking_app.dto.request.PermissionRequest;
import com.example.booking_app.dto.response.PermissionResponse;
import com.example.booking_app.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
