package com.example.booking_app.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.booking_app.dto.request.ServiceRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.ServiceResponse;
import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.User;
import com.example.booking_app.exception.AppException;
import com.example.booking_app.exception.ErrorCode;
import com.example.booking_app.repository.HotelRepository;
import com.example.booking_app.repository.UserRepository;
import com.example.booking_app.service.ServiceService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceController {

    ServiceService serviceService;
    UserRepository userRepository;
    HotelRepository hotelRepository;

    @GetMapping
    ApiResponse<List<ServiceResponse>> getAllByHotel() {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Hotel hotel = hotelRepository.findByUserId(user.getId()).orElseThrow();
        return ApiResponse.<List<ServiceResponse>>builder()
                .data(serviceService.getAllByHotel(hotel.getId()))
                .build();
    }

    @PostMapping
    ApiResponse<ServiceResponse> createService(@RequestBody ServiceRequest request) {
        return ApiResponse.<ServiceResponse>builder()
                .data(serviceService.createService(request))
                .build();
    }

    @PutMapping("/{serviceId}")
    ApiResponse<ServiceResponse> updateService(@RequestBody ServiceRequest request, @PathVariable Long serviceId) {
        return ApiResponse.<ServiceResponse>builder()
                .data(serviceService.updateService(serviceId, request))
                .build();
    }

    @DeleteMapping("/{serviceId}")
    ApiResponse<String> deleteService(@PathVariable Long serviceId) {
        serviceService.deleteService(serviceId);
        return ApiResponse.<String>builder().data("Delete Completed").build();
    }
}
