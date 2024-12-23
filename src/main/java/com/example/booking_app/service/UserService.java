package com.example.booking_app.service;

import java.util.List;
import java.util.Objects;

import com.example.booking_app.dto.request.UserUpdateRequest;
import com.example.booking_app.dto.response.RoleResponse;
import com.example.booking_app.entity.Role;
import com.example.booking_app.mapper.RoleMapper;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.booking_app.dto.request.UserCreationRequest;
import com.example.booking_app.dto.response.UserResponse;
import com.example.booking_app.entity.User;
import com.example.booking_app.exception.AppException;
import com.example.booking_app.exception.ErrorCode;
import com.example.booking_app.mapper.UserMapper;
import com.example.booking_app.repository.RoleRepository;
import com.example.booking_app.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;
    RoleMapper roleMapper;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserResponse userResponse = userMapper.toUserResponse(user);
                    RoleResponse roleResponse = roleMapper.toRoleResponse(user.getRole());
                    userResponse.setRole(roleResponse);
                    return userResponse;
                })
                .toList();
    }

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = roleRepository.findById(request.getRole()).orElseThrow();
        user.setRole(role);

        UserResponse userResponse = userMapper.toUserResponse(userRepository.save(user));
        userResponse.setRole(roleMapper.toRoleResponse(role));

        return userResponse;
    }

    public UserResponse getUserById(String id) {
        UserResponse userResponse = userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));

        return userResponse;
    }
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        UserResponse userResponse = userMapper.toUserResponse(user);
        userResponse.setRole(roleMapper.toRoleResponse(user.getRole()));

        return userResponse;
    }

    public UserResponse updateMyInfo(UserUpdateRequest request) {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);
        if (!Objects.isNull(request.getPassword())) user.setPassword(passwordEncoder.encode(user.getPassword()));



        UserResponse userResponse = userMapper.toUserResponse(userRepository.save(user));
        userResponse.setRole(roleMapper.toRoleResponse(user.getRole()));

        return userResponse;
    }

    public boolean checkExistUser(String username){
        boolean exists = userRepository.existsByUsername(username);
        return exists;
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }
}
