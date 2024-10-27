package com.example.booking_app.controller;

import org.springframework.web.bind.annotation.*;

import com.example.booking_app.dto.request.OrderBookingRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.OrderBookingResponse;
import com.example.booking_app.service.OrderBookingService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/orderBooking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderBookingController {
    OrderBookingService orderBookingService;

    @PostMapping
    ApiResponse<OrderBookingResponse> createOrderBooking(@RequestBody OrderBookingRequest request) {
        return ApiResponse.<OrderBookingResponse>builder()
                .data(orderBookingService.createOrder(request))
                .build();
    }
}
