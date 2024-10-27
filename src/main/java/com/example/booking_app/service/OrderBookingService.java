package com.example.booking_app.service;

import org.springframework.stereotype.Service;

import com.example.booking_app.dto.request.OrderBookingRequest;
import com.example.booking_app.dto.response.HotelResponse;
import com.example.booking_app.dto.response.OrderBookingResponse;
import com.example.booking_app.dto.response.UserResponse;
import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.OrderBooking;
import com.example.booking_app.entity.User;
import com.example.booking_app.exception.AppException;
import com.example.booking_app.exception.ErrorCode;
import com.example.booking_app.mapper.HotelMapper;
import com.example.booking_app.mapper.OrderBookingMapper;
import com.example.booking_app.mapper.UserMapper;
import com.example.booking_app.repository.HotelRepository;
import com.example.booking_app.repository.OrderBookingRepository;
import com.example.booking_app.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderBookingService {
    OrderBookingRepository orderBookingRepository;
    OrderBookingMapper orderBookingMapper;
    UserRepository userRepository;
    HotelRepository hotelRepository;
    UserMapper userMapper;
    HotelMapper hotelMapper;

    public OrderBookingResponse createOrder(OrderBookingRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        });

        UserResponse userResponse = userMapper.toUserResponse(user);

        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow(() -> {
            throw new AppException(ErrorCode.HOTEL_NOT_EXISTED);
        });

        HotelResponse hotelResponse = hotelMapper.toHotelResponse(hotel);

        OrderBooking orderBooking = orderBookingMapper.toOrderBooking(request);

        orderBooking.setUser(user);
        orderBooking.setHotel(hotel);

        OrderBookingResponse response =
                orderBookingMapper.toOrderBookingResponse(orderBookingRepository.save(orderBooking));
        response.setUser(userResponse);
        response.setHotel(hotelResponse);

        return response;
    }
}
