package com.example.booking_app.controller;

import com.example.booking_app.dto.request.BillRequest;
import com.example.booking_app.dto.request.BookRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.BillResponse;
import com.example.booking_app.dto.response.BookingResponse;
import com.example.booking_app.service.BillService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillController {

    BillService billService;
    @PostMapping
    ApiResponse<BillResponse> createHotel(@RequestBody BillRequest request) {
        return ApiResponse.<BillResponse>builder()
                .data(billService.createBill(request))
                .build();
    }
}
