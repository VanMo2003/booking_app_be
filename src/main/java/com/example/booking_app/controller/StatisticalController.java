package com.example.booking_app.controller;

import com.example.booking_app.dto.request.StatisticalRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.StatisticalResponse;
import com.example.booking_app.dto.response.UserResponse;
import com.example.booking_app.service.StatisticalService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistical")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatisticalController {
    StatisticalService statisticalService;

    @GetMapping
    ApiResponse<List<StatisticalResponse>> getAllByHotelByYear() {
        return ApiResponse.<List<StatisticalResponse>>builder()
                .data(statisticalService.getAllByHotelByYear())
                .build();
    }

    @PostMapping
    ApiResponse<StatisticalResponse> updateMonthlyStatistic(@RequestBody StatisticalRequest request) {
        return ApiResponse.<StatisticalResponse>builder()
                .data(statisticalService.updateMonthlyStatistic(request))
                .build();
    }
}
