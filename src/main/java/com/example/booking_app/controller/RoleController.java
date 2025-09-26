package com.example.booking_app.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.booking_app.dto.request.RoleRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.RoleResponse;
import com.example.booking_app.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @GetMapping
    ApiResponse<List<RoleResponse>> getAllRole() {
        return ApiResponse.<List<RoleResponse>>builder()
                .data(roleService.getAllRole())
                .build();
    }

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody @Valid RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .data(roleService.createRole(request))
                .build();
    }

    @DeleteMapping("/{name}")
    ApiResponse<String> deleteRole(@PathVariable String name) {
        roleService.deleteRole(name);
        return ApiResponse.<String>builder().data("Role has been deleted").build();
    }
}
