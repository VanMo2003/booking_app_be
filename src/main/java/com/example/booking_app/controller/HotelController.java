package com.example.booking_app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.booking_app.dto.request.HotelRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.HotelResponse;
import com.example.booking_app.service.HotelService;

import ch.qos.logback.core.util.StringUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelController {
    HotelService hotelService;

    @GetMapping
    ApiResponse<List<HotelResponse>> getAllHotel() {
        return ApiResponse.<List<HotelResponse>>builder()
                .data(hotelService.getAllHotel())
                .build();
    }

    @PostMapping
    ApiResponse<HotelResponse> createHotel(@RequestBody HotelRequest request) {
        return ApiResponse.<HotelResponse>builder()
                .data(hotelService.createHotel(request))
                .build();
    }
    @GetMapping("/{id}")
    ApiResponse<HotelResponse> getAllHotel(@PathVariable Long id) {
        return ApiResponse.<HotelResponse>builder()
                .data(hotelService.getHotelById(id))
                .build();
    }
    @PutMapping("/{id}")
    ApiResponse<HotelResponse> updateHotel(@PathVariable Long id, @RequestBody HotelRequest request) {
        return ApiResponse.<HotelResponse>builder()
                .data(hotelService.updateHotel(id, request))
                .build();
    }

    @GetMapping("/search")
    ApiResponse<List<HotelResponse>> searchByNameHotel(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "address", required = false) String address) {
        List<HotelResponse> hotels;
        if (!StringUtil.isNullOrEmpty(name) && !StringUtil.isNullOrEmpty(address)) {
            hotels = hotelService.searchUsersByNameAndAddress(name, address);
        } else if (!StringUtil.isNullOrEmpty(name)) {
            hotels = hotelService.searchHotelByName(name);
        } else if (!StringUtil.isNullOrEmpty(address)) {
            hotels = hotelService.searchHotelByAddress(address);
        } else {
            hotels = List.of();
        }
        return ApiResponse.<List<HotelResponse>>builder().data(hotels).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ApiResponse.<String>builder().data("Hotel has been deleted").build();
    }
}
