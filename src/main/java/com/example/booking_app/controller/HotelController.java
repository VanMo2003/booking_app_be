package com.example.booking_app.controller;

import com.example.booking_app.dto.request.HotelRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.HotelResponse;
import com.example.booking_app.repository.HotelRepository;
import com.example.booking_app.service.HotelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelController {
    HotelService hotelService;
    @GetMapping
    ApiResponse<List<HotelResponse>> getAllHotel(){
        return  ApiResponse.<List<HotelResponse>>builder()
                .data(hotelService.getAllHotel())
                .build();
    }

    @PostMapping
    ApiResponse<HotelResponse> createHotel(@RequestBody HotelRequest request){
        return ApiResponse.<HotelResponse>builder()
                .data(hotelService.createHotel(request))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<HotelResponse> updateHotel(@PathVariable Long id, @RequestBody HotelRequest request){
        return ApiResponse.<HotelResponse>builder()
                .data(hotelService.updateHotel(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteHotel(@PathVariable Long id){
        hotelService.deleteHotel(id);
        return ApiResponse.<String>builder()
                .data("Hotel has been deleted")
                .build();
    }
}
