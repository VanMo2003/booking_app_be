package com.example.booking_app.service;

import java.util.*;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.booking_app.constant.StatusOrder;
import com.example.booking_app.dto.request.BookingRequest;
import com.example.booking_app.dto.response.BookedRoomResponse;
import com.example.booking_app.dto.response.BookingResponse;
import com.example.booking_app.dto.response.RoomResponse;
import com.example.booking_app.dto.response.ServiceResponse;
import com.example.booking_app.entity.BookedRoom;
import com.example.booking_app.entity.Booking;
import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.User;
import com.example.booking_app.exception.AppException;
import com.example.booking_app.exception.ErrorCode;
import com.example.booking_app.mapper.BookedRoomMapper;
import com.example.booking_app.mapper.BookingMapper;
import com.example.booking_app.mapper.RoomMapper;
import com.example.booking_app.mapper.ServiceMapper;
import com.example.booking_app.repository.BookingRepository;
import com.example.booking_app.repository.HotelRepository;
import com.example.booking_app.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

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
    HotelRepository hotelRepository;

    List<String> orderOfStatuses = Arrays.asList("PENDING", "CONFIRMED", "COMPLETED", "CANCELED");

    @PreAuthorize("hasRole('USER')")
    public List<BookingResponse> getBookingByUser() {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        List<BookingResponse> bookingResponses = new ArrayList<>();

        bookingRepository.findByUser(user).stream()
                .map(booking -> bookingResponses.add(convertBookingToBookingResponse(booking, booking.getBookedRoom())))
                .toList();

        bookingResponses.sort(Comparator.comparingInt(
                booking -> orderOfStatuses.indexOf(booking.getStatusOrder().name())));

        return bookingResponses;
    }

    @PreAuthorize("hasRole('HOTELIER')")
    public List<BookingResponse> getBookingByHotel() {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Hotel hotel = hotelRepository.findByUserId(user.getId()).orElseThrow();

        List<BookingResponse> bookingResponses = new ArrayList<>();
        bookingRepository.findByHotel(hotel.getId()).stream()
                .map(booking -> bookingResponses.add(convertBookingToBookingResponse(booking, booking.getBookedRoom())))
                .toList();

        bookingResponses.sort(Comparator.comparingInt((BookingResponse booking) ->
                        orderOfStatuses.indexOf(booking.getStatusOrder().name()))
                .thenComparing(BookingResponse::getBookingDate));

        return bookingResponses;
    }

    public BookingResponse createBookingService(BookingRequest request, BookedRoom bookedRoom) {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Booking booking = bookingMapper.toBooking(request);
        booking.setUser(user);
        booking.setBookedRoom(bookedRoom);
        bookingRepository.save(booking);

        BookingResponse bookingResponse = convertBookingToBookingResponse(booking, bookedRoom);

        return bookingResponse;
    }

    public String pendingOrder(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow();

        booking.setStatusOrder(StatusOrder.PENDING);

        bookingRepository.save(booking);

        return "Cập nhật trạng thái thành công";
    }

    public String confirmOrder(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow();

        booking.setStatusOrder(StatusOrder.CONFIRMED);

        bookingRepository.save(booking);

        return "Cập nhật trạng thái thành công";
    }

    public String cancelOrder(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow();

        booking.setStatusOrder(StatusOrder.CANCELED);

        bookingRepository.save(booking);

        return "Cập nhật trạng thái thành công";
    }

    private BookingResponse convertBookingToBookingResponse(Booking booking, BookedRoom bookedRoom) {
        BookingResponse bookingResponse = bookingMapper.toBookingResponse(booking);

        List<RoomResponse> roomResponses = bookedRoom.getRooms().stream()
                .map(room -> roomMapper.toRoomResponse(room))
                .toList();
        List<ServiceResponse> serviceResponses = bookedRoom.getServices().stream()
                .map(service -> serviceMapper.toServiceResponse(service))
                .toList();

        BookedRoomResponse bookedRoomResponse = bookedRoomMapper.toBookedRoomResponse(booking.getBookedRoom());
        bookedRoomResponse.setRooms(roomResponses);
        bookedRoomResponse.setServices(serviceResponses);

        bookingResponse.setBookedRoom(bookedRoomResponse);

        return bookingResponse;
    }
}
