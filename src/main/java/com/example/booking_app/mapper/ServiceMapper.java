package com.example.booking_app.mapper;


import com.example.booking_app.dto.request.ServiceRequest;
import com.example.booking_app.dto.request.UserUpdateRequest;
import com.example.booking_app.dto.response.ServiceResponse;
import com.example.booking_app.entity.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    @Mapping(target = "hotel", ignore = true)
    Service toService(ServiceRequest request);
    @Mapping(target = "hotel", ignore = true)
    ServiceResponse toServiceResponse(Service service);

    @Mapping(target = "hotel", ignore = true)
    void updateService(@MappingTarget Service service, ServiceRequest request);
}
