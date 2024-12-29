package com.example.booking_app.controller;

import com.example.booking_app.dto.request.HotelReviewRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.HotelReviewResponse;
import com.example.booking_app.service.HotelReviewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotelReview")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelReviewController {

    private HotelReviewService hotelReviewService;

    @GetMapping("/{hotelId}")
    public ApiResponse<List<HotelReviewResponse>> getAllReviews(@PathVariable Long hotelId) {
        return ApiResponse.<List<HotelReviewResponse>>builder()
                .data(hotelReviewService.getAllReviews(hotelId))
                .build();
    }

    @PostMapping
    public ApiResponse<HotelReviewResponse> createReview(@RequestBody HotelReviewRequest request) {
        return ApiResponse.<HotelReviewResponse>builder()
                .data(hotelReviewService.createReview(request))
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        hotelReviewService.deleteReview(id);
    }
}
