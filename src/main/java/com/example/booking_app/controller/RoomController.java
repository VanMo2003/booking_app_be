package com.example.booking_app.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.booking_app.dto.request.RoomRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.RoomResponse;
import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.User;
import com.example.booking_app.exception.AppException;
import com.example.booking_app.exception.ErrorCode;
import com.example.booking_app.repository.HotelRepository;
import com.example.booking_app.repository.UserRepository;
import com.example.booking_app.service.RoomService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomController {

    RoomService roomService;
    UserRepository userRepository;
    HotelRepository hotelRepository;

    @GetMapping
    ApiResponse<List<RoomResponse>> getAllByHotel() {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Hotel hotel = hotelRepository.findByUserId(user.getId()).orElseThrow();
        return ApiResponse.<List<RoomResponse>>builder()
                .data(roomService.getAllByHotel(hotel.getId()))
                .build();
    }

    @PostMapping
    ApiResponse<RoomResponse> createRoom(@RequestBody RoomRequest request) {
        return ApiResponse.<RoomResponse>builder()
                .data(roomService.createRoom(request))
                .build();
    }

    @PutMapping("/{roomId}")
    ApiResponse<RoomResponse> updateRoom(@RequestBody RoomRequest request, @PathVariable Long roomId) {
        return ApiResponse.<RoomResponse>builder()
                .data(roomService.updateRoom(roomId, request))
                .build();
    }

    @DeleteMapping("/{roomId}")
    ApiResponse<String> deleteSRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        return ApiResponse.<String>builder().data("Delete Completed").build();
    }
}
