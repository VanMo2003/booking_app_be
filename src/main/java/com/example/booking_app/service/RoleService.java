package com.example.booking_app.service;

import com.example.booking_app.dto.request.RoleRequest;
import com.example.booking_app.dto.response.RoleResponse;
import com.example.booking_app.entity.Role;
import com.example.booking_app.mapper.RoleMapper;
import com.example.booking_app.repository.PermissionRepository;
import com.example.booking_app.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    static Logger log = LoggerFactory.getLogger(RoleService.class);
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @PreAuthorize("hasRole('ADMIN')") // chặn trước khi gọi hàm để kiểm tra role
    public List<RoleResponse> getAllRole() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')") // chặn trước khi gọi hàm để kiểm tra role
    public RoleResponse createRole(RoleRequest request) {
        Role role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @PreAuthorize("hasRole('ADMIN')") // chặn trước khi gọi hàm để kiểm tra role
    public void deleteRole(String name) {
        roleRepository.deleteById(name);
    }
}
