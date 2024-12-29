package com.example.booking_app.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.*;

import com.example.booking_app.dto.request.AuthenticationRequest;
import com.example.booking_app.dto.request.IntrospectRequest;
import com.example.booking_app.dto.request.LogoutRequest;
import com.example.booking_app.dto.request.RefreshTokenRequest;
import com.example.booking_app.dto.response.ApiResponse;
import com.example.booking_app.dto.response.AuthenticationResponse;
import com.example.booking_app.dto.response.IntrospectResponse;
import com.example.booking_app.repository.HotelRepository;
import com.example.booking_app.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticated(request);
        return ApiResponse.<AuthenticationResponse>builder().data(result).build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().data(result).build();
    }

    @PostMapping("/logout")
    ApiResponse<String> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<String>builder().data("logout success").build();
    }

    @PostMapping("/refreshToken")
    ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder().data(result).build();
    }
}
