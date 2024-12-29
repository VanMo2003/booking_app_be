package com.example.booking_app.service;

import com.example.booking_app.dto.request.BillRequest;
import com.example.booking_app.dto.response.BillResponse;
import com.example.booking_app.dto.response.BookingResponse;
import com.example.booking_app.dto.response.RoomResponse;
import com.example.booking_app.dto.response.ServiceResponse;
import com.example.booking_app.entity.Bill;
import com.example.booking_app.entity.Booking;
import com.example.booking_app.mapper.*;
import com.example.booking_app.repository.BillRepository;
import com.example.booking_app.repository.BookingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillService {
    BillRepository billRepository;
    BillMapper billMapper;
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    BookedRoomMapper bookedRoomMapper;
    RoomMapper roomMapper;
    ServiceMapper serviceMapper;
    public BillResponse createBill(BillRequest request){
        Booking booking = bookingRepository.findById(request.getBookingId()).orElseThrow();
        BookingResponse bookingResponse = bookingMapper.toBookingResponse(booking);
        List<RoomResponse> roomResponses = booking.getBookedRoom().getRooms().stream().map(room -> roomMapper.toRoomResponse(room)).toList();
        List<ServiceResponse> serviceResponses = booking.getBookedRoom().getServices().stream().map(service -> serviceMapper.toServiceResponse(service)).toList();

        bookingResponse.setBookedRoom(bookedRoomMapper.toBookedRoomResponse(booking.getBookedRoom()));
        bookingResponse.getBookedRoom().setRooms(roomResponses);
        bookingResponse.getBookedRoom().setServices(serviceResponses);

        Bill bill = billMapper.toBill(request);
        bill.setBooking(booking);

        BillResponse billResponse = billMapper.toBillResponse(billRepository.save(bill));
        billResponse.setBooking(bookingResponse);

        return billResponse;
    }
}
