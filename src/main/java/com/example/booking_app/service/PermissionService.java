package com.example.booking_app.service;

import com.example.booking_app.dto.request.PermissionRequest;
import com.example.booking_app.dto.response.PermissionResponse;
import com.example.booking_app.entity.Permission;
import com.example.booking_app.mapper.PermissionMapper;
import com.example.booking_app.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    private static final Logger log = LoggerFactory.getLogger(PermissionService.class);
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @PreAuthorize("hasRole('ADMIN')") // chặn trước khi gọi hàm để kiểm tra role
    public List<PermissionResponse> getAllPermission() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')") // chặn trước khi gọi hàm để kiểm tra role
    public PermissionResponse createPermission(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    @PreAuthorize("hasRole('ADMIN')") // chặn trước khi gọi hàm để kiểm tra role
    public void deletePermission(String name) {
        permissionRepository.deleteById(name);
    }
}
