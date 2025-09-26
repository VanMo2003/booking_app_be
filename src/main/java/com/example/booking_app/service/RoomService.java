package com.example.booking_app.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.booking_app.dto.request.RoomRequest;
import com.example.booking_app.dto.response.RoomResponse;
import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.Room;
import com.example.booking_app.entity.User;
import com.example.booking_app.exception.AppException;
import com.example.booking_app.exception.ErrorCode;
import com.example.booking_app.mapper.HotelMapper;
import com.example.booking_app.mapper.RoomMapper;
import com.example.booking_app.repository.HotelRepository;
import com.example.booking_app.repository.RoomRepository;
import com.example.booking_app.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomService {
    RoomRepository roomRepository;
    HotelRepository hotelRepository;
    RoomMapper roomMapper;
    HotelMapper hotelMapper;
    UserRepository userRepository;

    public List<RoomResponse> getAllByHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> {
            throw new AppException(ErrorCode.HOTEL_NOT_EXISTED);
        });
        List<RoomResponse> roomResponses = roomRepository.findAllByHotel(hotel).stream()
                .map(room -> roomMapper.toRoomResponse(room))
                .toList();

        return roomResponses;
    }

    @PreAuthorize("hasRole('HOTELIER')")
    public RoomResponse createRoom(RoomRequest request) {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Hotel hotel = hotelRepository.findByUserId(user.getId()).orElseThrow();

        Room room = roomMapper.toRoom(request);
        room.setHotel(hotel);

        roomRepository.save(room);

        RoomResponse roomResponse = roomMapper.toRoomResponse(room);
        roomResponse.setHotel(hotelMapper.toHotelResponse(hotel));

        return roomResponse;
    }

    @PreAuthorize("hasRole('HOTELIER')")
    public RoomResponse updateRoom(Long id, RoomRequest request) {

        Room room = roomRepository.findById(id).orElseThrow();

        roomMapper.updateRoom(room, request);

        RoomResponse roomResponse = roomMapper.toRoomResponse(roomRepository.save(room));

        return roomResponse;
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
