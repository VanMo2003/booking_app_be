package com.example.booking_app.service;

import com.example.booking_app.dto.request.BookRequest;
import com.example.booking_app.dto.request.BookedRoomRequest;
import com.example.booking_app.dto.request.BookingRequest;
import com.example.booking_app.dto.response.BookedRoomResponse;
import com.example.booking_app.dto.response.BookingResponse;
import com.example.booking_app.dto.response.RoomResponse;
import com.example.booking_app.dto.response.ServiceResponse;
import com.example.booking_app.entity.*;
import com.example.booking_app.exception.AppException;
import com.example.booking_app.exception.ErrorCode;
import com.example.booking_app.mapper.BookedRoomMapper;
import com.example.booking_app.mapper.BookingMapper;
import com.example.booking_app.mapper.RoomMapper;
import com.example.booking_app.mapper.ServiceMapper;
import com.example.booking_app.repository.BookedRoomRepository;
import com.example.booking_app.repository.HotelRepository;
import com.example.booking_app.repository.RoomRepository;
import com.example.booking_app.repository.ServiceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookedRoomService {
    BookingService bookingService;
    BookedRoomRepository bookedRoomRepository;
    HotelRepository hotelRepository;
    BookedRoomMapper bookedRoomMapper;
    BookingMapper bookingMapper;
    RoomRepository roomRepository;
    ServiceRepository serviceRepository;
    RoomMapper roomMapper;
    ServiceMapper serviceMapper;

    public BookingResponse createBookedRoom(BookRequest bookRequest){
        BookedRoomRequest request = bookedRoomMapper.toBookedRoomRequest(bookRequest);

        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow();

        if (!checkExistBookedRoom(request, hotel)) {
            BookedRoom bookedRoom = bookedRoomMapper.toBookedRoom(request);
            BookingRequest bookingRequest = bookingMapper.toBookingRequest(bookRequest);

            List<Room> rooms = new ArrayList<>();
            List<Service> services = new ArrayList<>();

            if (!request.getRooms().isEmpty()) {
                rooms = roomRepository.findAllById(request.getRooms());

                if (rooms.isEmpty())
                    throw new AppException(ErrorCode.ROOM_NOT_EXISTED);

                for (Room room : rooms) {
                    bookingRequest.setPrice(bookingRequest.getPrice() + room.getPrice());
                }
            }
            if (!request.getServices().isEmpty()) {
                services = serviceRepository.findAllById(request.getServices());
                if (services.isEmpty())
                    throw new AppException(ErrorCode.SERVICE_NOT_EXISTED);
                for (Service service : services) {
                    bookingRequest.setPrice(bookingRequest.getPrice() + service.getPrice());
                }
            }

            bookedRoom.setRooms(rooms);
            bookedRoom.setServices(services);
            bookedRoom.setHotel(hotel);
            bookedRoomRepository.save(bookedRoom);

            BookingResponse bookingResponse = bookingService.createBookingService(bookingRequest, bookedRoom);

            BookedRoomResponse bookedRoomResponse = bookedRoomMapper.toBookedRoomResponse(bookedRoom);
            bookedRoomResponse.setRooms(rooms.stream().map(room -> roomMapper.toRoomResponse(room)).toList());
            bookedRoomResponse.setServices(services.stream().map(service -> serviceMapper.toServiceResponse(service)).toList());


            return bookingResponse;
        }

        throw new AppException(ErrorCode.BOOKED_ROOM_EXISTED);
    }

    private boolean checkExistBookedRoom(BookedRoomRequest request, Hotel hotel) {
//        List<BookedRoom> bookedRooms = bookedRoomRepository.findByArrivalDateOrDepartureDateAndHotel(request.getArrivalDate(), request.getDepartureDate(), hotel);
        List<BookedRoom> bookedRooms = bookedRoomRepository.getAllBookedRoomIntersectArrivalDateAndDepartureDate(request.getArrivalDate(), request.getDepartureDate(), hotel.getId());

        for (BookedRoom bookedRoom: bookedRooms) {
            for (Room room: bookedRoom.getRooms()) {
                if (request.getRooms().contains(room.getId())){
                    return true;
                }
            }
        }

        return false;
    }
}
