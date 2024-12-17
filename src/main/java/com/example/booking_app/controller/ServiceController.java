package com.example.booking_app.controller;


import com.example.booking_app.dto.request.ServiceRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.ServiceResponse;
import com.example.booking_app.service.ServiceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceController {

    ServiceService serviceService;

    @GetMapping("/{hotelId}")
    ApiResponse<List<ServiceResponse>> getAllByHotel(@PathVariable Long hotelId){
        return ApiResponse.<List<ServiceResponse>>builder()
                .data(serviceService.getAllByHotel(hotelId))
                .build();
    }

    @PostMapping
    ApiResponse<ServiceResponse> createService(@RequestBody ServiceRequest request){
        return ApiResponse.<ServiceResponse>builder()
                .data(serviceService.createService(request))
                .build();
    }

    @PutMapping("/{serviceId}")
    ApiResponse<ServiceResponse> updateService(@RequestBody ServiceRequest request, @PathVariable Long serviceId){
        return ApiResponse.<ServiceResponse>builder()
                .data(serviceService.updateService(serviceId, request))
                .build();
    }

    @DeleteMapping("/{serviceId}")
    ApiResponse<String> deleteService(@PathVariable Long serviceId){
        serviceService.deleteService(serviceId);
        return ApiResponse.<String>builder()
                .data("Delete Completed")
                .build();
    }
}
