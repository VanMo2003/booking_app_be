package com.example.booking_app.controller;


import com.example.booking_app.dto.request.RoomRequest;
import com.example.booking_app.dto.request.ServiceRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.RoomResponse;
import com.example.booking_app.dto.response.ServiceResponse;
import com.example.booking_app.service.RoomService;
import com.example.booking_app.service.ServiceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomController {

    RoomService roomService;

    @GetMapping("/{hotelId}")
    ApiResponse<List<RoomResponse>> getAllByHotel(@PathVariable Long hotelId){
        return ApiResponse.<List<RoomResponse>>builder()
                .data(roomService.getAllByHotel(hotelId))
                .build();
    }

    @PostMapping
    ApiResponse<RoomResponse> createRoom(@RequestBody RoomRequest request){
        return ApiResponse.<RoomResponse>builder()
                .data(roomService.createRoom(request))
                .build();
    }

    @PutMapping("/{roomId}")
    ApiResponse<RoomResponse> updateRoom(@RequestBody RoomRequest request, @PathVariable Long roomId){
        return ApiResponse.<RoomResponse>builder()
                .data(roomService.updateRoom(roomId, request))
                .build();
    }

    @DeleteMapping("/{roomId}")
    ApiResponse<String> deleteSRoom(@PathVariable Long roomId){
        roomService.deleteRoom(roomId);
        return ApiResponse.<String>builder()
                .data("Delete Completed")
                .build();
    }
}
