package com.example.booking_app.mapper;


import com.example.booking_app.dto.request.UserCreationRequest;
import com.example.booking_app.dto.response.UserResponse;
import com.example.booking_app.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);
//
//    @Mapping(target = "roles", ignore = true)
//    @Mapping(target = "password", ignore = true)
//    void updateUser(@MappingTarget User user, UserUpdateRequest request);
//
//    @Mapping(target = "roles", ignore = true)
//    void updateRole(@MappingTarget User user, UserUpdateRoleRequest request);

}
