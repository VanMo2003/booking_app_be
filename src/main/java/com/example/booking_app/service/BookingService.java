package com.example.booking_app.service;

import com.example.booking_app.dto.request.BookingRequest;
import com.example.booking_app.dto.response.BookedRoomResponse;
import com.example.booking_app.dto.response.BookingResponse;
import com.example.booking_app.dto.response.RoomResponse;
import com.example.booking_app.dto.response.ServiceResponse;
import com.example.booking_app.entity.BookedRoom;
import com.example.booking_app.entity.Booking;
import com.example.booking_app.entity.User;
import com.example.booking_app.exception.AppException;
import com.example.booking_app.exception.ErrorCode;
import com.example.booking_app.mapper.BookedRoomMapper;
import com.example.booking_app.mapper.BookingMapper;
import com.example.booking_app.mapper.RoomMapper;
import com.example.booking_app.mapper.ServiceMapper;
import com.example.booking_app.repository.BookingRepository;
import com.example.booking_app.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {
    BookingRepository bookingRepository;
    UserRepository userRepository;
    BookingMapper bookingMapper;
    BookedRoomMapper bookedRoomMapper;
    RoomMapper roomMapper;
    ServiceMapper serviceMapper;

    public List<BookingResponse> getMySelf(){
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        List<BookingResponse> bookings = bookingRepository.findByUser(user).stream().map(booking -> {
            BookingResponse bookingResponse = convertBookingToBookingResponse(booking, booking.getBookedRoom());

            return  bookingResponse;
        }).toList();

        return bookings;
    }

    public BookingResponse createBookingService(BookingRequest request, BookedRoom bookedRoom){
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Booking booking = bookingMapper.toBooking(request);
        booking.setUser(user);
        booking.setBookedRoom(bookedRoom);
        bookingRepository.save(booking);

        BookingResponse bookingResponse = convertBookingToBookingResponse(booking, bookedRoom);

        return bookingResponse;
    }

    private BookingResponse convertBookingToBookingResponse(Booking booking, BookedRoom bookedRoom){
        BookingResponse bookingResponse = bookingMapper.toBookingResponse(booking);

        List<RoomResponse> roomResponses = bookedRoom.getRooms().stream().map(room -> roomMapper.toRoomResponse(room)).toList();
        List<ServiceResponse> serviceResponses = bookedRoom.getServices().stream().map(service -> serviceMapper.toServiceResponse(service)).toList();

        BookedRoomResponse bookedRoomResponse = bookedRoomMapper.toBookedRoomResponse(booking.getBookedRoom());
        bookedRoomResponse.setRooms(roomResponses);
        bookedRoomResponse.setServices(serviceResponses);

        bookingResponse.setBookedRoom(bookedRoomResponse);

        return bookingResponse;
    }
}
