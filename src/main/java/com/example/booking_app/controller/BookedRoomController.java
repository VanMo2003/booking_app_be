package com.example.booking_app.controller;

import ch.qos.logback.core.util.StringUtil;
import com.example.booking_app.dto.request.BookRequest;
import com.example.booking_app.dto.request.BookedRoomRequest;
import com.example.booking_app.dto.request.HotelRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.BookedRoomResponse;
import com.example.booking_app.dto.response.HotelResponse;
import com.example.booking_app.service.BookedRoomService;
import com.example.booking_app.service.HotelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookedRoom")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookedRoomController {
    BookedRoomService bookedRoomService;

    @PostMapping
    ApiResponse<BookedRoomResponse> createHotel(@RequestBody BookRequest request) {
        return ApiResponse.<BookedRoomResponse>builder()
                .data(bookedRoomService.createBookedRoom(request))
                .build();
    }
}
