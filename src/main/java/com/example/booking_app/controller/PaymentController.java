package com.example.booking_app.controller;


import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.PaymentResponse;
import com.example.booking_app.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {
    PaymentService paymentService;
    @GetMapping("/vn-pay")
    public ApiResponse<PaymentResponse> pay(HttpServletRequest request) {
        return ApiResponse.<PaymentResponse>builder()
                .data(paymentService.createVnPayPayment(request))
                .build();
    }
    @GetMapping("/vn-pay-callback")
    public ApiResponse<PaymentResponse> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            return ApiResponse.<PaymentResponse>builder()
                    .data(new PaymentResponse("00", "Success", ""))
                    .build();
        } else {
            return ApiResponse.<PaymentResponse>builder()
                    .message("Failed")
                    .data(null)
                    .build();
        }
    }
}
