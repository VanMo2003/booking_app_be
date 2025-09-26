package com.example.booking_app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.BookingResponse;
import com.example.booking_app.service.BookingService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {
    BookingService bookingService;

    @GetMapping("/getBookingByUser")
    ApiResponse<List<BookingResponse>> getBookingByUser() {
        return ApiResponse.<List<BookingResponse>>builder()
                .data(bookingService.getBookingByUser())
                .build();
    }

    @GetMapping("/getBookingByHotel")
    ApiResponse<List<BookingResponse>> getBookingByHotel() {
        return ApiResponse.<List<BookingResponse>>builder()
                .data(bookingService.getBookingByHotel())
                .build();
    }

    @PutMapping("/confirmOrder/{id}")
    ApiResponse<String> confirmOrder(@PathVariable Long id) {
        return ApiResponse.<String>builder()
                .data(bookingService.confirmOrder(id))
                .build();
    }

    @PutMapping("/pendingOrder/{id}")
    ApiResponse<String> pendingOrder(@PathVariable Long id) {
        return ApiResponse.<String>builder()
                .data(bookingService.pendingOrder(id))
                .build();
    }

    @PutMapping("/cancelOrder/{id}")
    ApiResponse<String> cancelOrder(@PathVariable Long id) {
        return ApiResponse.<String>builder()
                .data(bookingService.cancelOrder(id))
                .build();
    }
}
