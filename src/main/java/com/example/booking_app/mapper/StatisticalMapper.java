package com.example.booking_app.mapper;

import com.example.booking_app.dto.request.StatisticalRequest;
import com.example.booking_app.dto.request.UserCreationRequest;
import com.example.booking_app.dto.request.UserUpdateRequest;
import com.example.booking_app.dto.response.StatisticalResponse;
import com.example.booking_app.dto.response.UserResponse;
import com.example.booking_app.entity.Statistical;
import com.example.booking_app.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StatisticalMapper {
    @Mapping(target = "hotel", ignore = true)
    Statistical toStatistical(StatisticalRequest request);

    @Mapping(target = "hotel", ignore = true)
    StatisticalResponse toStatisticalResponse(Statistical statistical);

    @Mapping(target = "hotel", ignore = true)
    void updateStatistical(@MappingTarget Statistical statistical, StatisticalRequest request);
}
