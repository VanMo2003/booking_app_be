package com.example.booking_app.service;

import com.example.booking_app.dto.request.BookedRoomRequest;
import com.example.booking_app.dto.response.BookedRoomResponse;
import com.example.booking_app.entity.BookedRoom;
import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.Room;
import com.example.booking_app.entity.Service;
import com.example.booking_app.exception.AppException;
import com.example.booking_app.exception.ErrorCode;
import com.example.booking_app.mapper.BookedRoomMapper;
import com.example.booking_app.repository.BookedRoomRepository;
import com.example.booking_app.repository.HotelRepository;
import com.example.booking_app.repository.RoomRepository;
import com.example.booking_app.repository.ServiceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookedRoomService {

    BookedRoomRepository bookedRoomRepository;
    HotelRepository hotelRepository;
    BookedRoomMapper bookedRoomMapper;
    RoomRepository roomRepository;
    ServiceRepository serviceRepository;

    public BookedRoomResponse createBookedRoom(BookedRoomRequest request){
        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow();
        BookedRoom bookedRoom = bookedRoomMapper.toBookedRoom(request);

        if (!checkExistBookedRoom(request, hotel)) {
            List<Room> rooms = null;
            List<Service> services = null;

            if (!request.getRooms().isEmpty()) {
                rooms = roomRepository.findAllById(request.getRooms());

                if (rooms.isEmpty())
                    throw new AppException(ErrorCode.ROOM_NOT_EXISTED);

                for (Room room : rooms) {
                    bookedRoom.setPrice(bookedRoom.getPrice() + room.getPrice());
                }
            } else if (!request.getServices().isEmpty()) {
                services = serviceRepository.findAllById(request.getServices());
                if (services.isEmpty())
                    throw new AppException(ErrorCode.SERVICE_NOT_EXISTED);
                for (Service service : services) {
                    bookedRoom.setPrice(bookedRoom.getPrice() + service.getPrice());
                }
            }

            bookedRoom.setRooms(rooms);
            bookedRoom.setServices(services);
            bookedRoom.setHotel(hotel);

            return bookedRoomMapper.toBookedRoomResponse(bookedRoomRepository.save(bookedRoom));
        }

        throw new AppException(ErrorCode.BOOKED_ROOM_EXISTED);
    }

    private boolean checkExistBookedRoom(BookedRoomRequest request, Hotel hotel) {
        List<BookedRoom> bookedRooms = bookedRoomRepository.findByArrivalDateOrDepartureDateAndHotel(request.getArrivalDate(), request.getDepartureDate(), hotel);

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
