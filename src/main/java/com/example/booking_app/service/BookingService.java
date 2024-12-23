package com.example.booking_app.service;

import com.example.booking_app.dto.request.BookingRequest;
import com.example.booking_app.dto.response.BookingResponse;
import com.example.booking_app.entity.BookedRoom;
import com.example.booking_app.entity.Booking;
import com.example.booking_app.entity.User;
import com.example.booking_app.exception.AppException;
import com.example.booking_app.exception.ErrorCode;
import com.example.booking_app.mapper.BookingMapper;
import com.example.booking_app.repository.BookingRepository;
import com.example.booking_app.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {
    BookingRepository bookingRepository;
    UserRepository userRepository;
    BookingMapper bookingMapper;

    public List<BookingResponse> getBookingByUser(String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        List<BookingResponse> bookings = bookingRepository.findByUser(user).stream().map(booking -> bookingMapper.toBookingResponse(booking)).toList();

        return bookings;
    }

    public void createBookingService(BookingRequest request, BookedRoom bookedRoom){
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Booking booking = bookingMapper.toBooking(request);
        booking.setUser(user);
        booking.setBookedRoom(bookedRoom);


        bookingRepository.save(booking);
    }
}
