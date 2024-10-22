package com.example.booking_app.controller;


import com.example.booking_app.dto.request.UserCreationRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.UserResponse;
import com.example.booking_app.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping
    ApiResponse<List<UserResponse>> getAllUsers() {
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setData(userService.getAllUsers());
        return response;
    }

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setData(userService.createUser(request));
        return response;
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUserById(@PathVariable String userId) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setData(userService.getUserById(userId));
        return response;
    }

    @DeleteMapping("{userId}")
    ApiResponse<String> deleteUserById(@PathVariable String userId) {
        userService.deleteUserById(userId);
        ApiResponse response = new ApiResponse<>();
        response.setData("User has been deleted");
        return response;
    }
}