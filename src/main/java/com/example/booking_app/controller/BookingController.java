package com.example.booking_app.controller;

import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.BookingResponse;
import com.example.booking_app.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {
    BookingService bookingService;

    @GetMapping("/getMySelf")
    ApiResponse<List<BookingResponse>> getMySelf() {
        return ApiResponse.<List<BookingResponse>>builder()
                .data(bookingService.getMySelf())
                .build();
    }

    @GetMapping("/statistical")
    ApiResponse<List<BookingResponse>> statistical() {
        return ApiResponse.<List<BookingResponse>>builder()
                .data(bookingService.getMySelf())
                .build();
    }
}
