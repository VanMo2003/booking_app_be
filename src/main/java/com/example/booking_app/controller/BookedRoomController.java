package com.example.booking_app.controller;

import org.springframework.web.bind.annotation.*;

import com.example.booking_app.dto.request.OrderRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.BookingResponse;
import com.example.booking_app.service.BookedRoomService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/bookedRoom")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookedRoomController {
    BookedRoomService bookedRoomService;

    @PostMapping
    ApiResponse<BookingResponse> createBookedRoom(@RequestBody OrderRequest request) {
        return ApiResponse.<BookingResponse>builder()
                .data(bookedRoomService.createBookedRoom(request))
                .build();
    }
}
