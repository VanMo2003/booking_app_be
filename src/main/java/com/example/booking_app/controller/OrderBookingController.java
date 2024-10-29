package com.example.booking_app.controller;

import java.util.List;

import com.example.booking_app.dto.request.UpdateStatusRequest;
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

    @GetMapping
    ApiResponse<List<OrderBookingResponse>> getAllOrder() {
        return ApiResponse.<List<OrderBookingResponse>>builder()
                .data(orderBookingService.getAllOrder())
                .build();
    }

    @PostMapping
    ApiResponse<OrderBookingResponse> createOrder(@RequestBody OrderBookingRequest request) {
        return ApiResponse.<OrderBookingResponse>builder()
                .data(orderBookingService.createOrder(request))
                .build();
    }

    @PutMapping("/confirmOrder/{id}")
    ApiResponse<OrderBookingResponse> confirmOrder(@PathVariable Long id){
        return ApiResponse.<OrderBookingResponse>builder()
                .data(orderBookingService.updateStatusOrder(id, true))
                .build();
    }
    @PutMapping("/cancelOrder/{id}")
    ApiResponse<OrderBookingResponse> cancelOrder(@PathVariable Long id){
        return ApiResponse.<OrderBookingResponse>builder()
                .data(orderBookingService.updateStatusOrder(id, false))
                .build();
    }
}
