package com.example.booking_app.controller;

import com.example.booking_app.dto.request.RoleRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.RoleResponse;
import com.example.booking_app.service.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
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
