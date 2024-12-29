package com.example.booking_app.mapper;

import com.example.booking_app.dto.request.BookRequest;
import com.example.booking_app.dto.request.BookedRoomRequest;
import com.example.booking_app.dto.request.HotelRequest;
import com.example.booking_app.dto.response.BookedRoomResponse;
import com.example.booking_app.dto.response.HotelResponse;
import com.example.booking_app.entity.BookedRoom;
import com.example.booking_app.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookedRoomMapper {
    @Mapping(target = "hotel", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "services", ignore = true)
    BookedRoom toBookedRoom(BookedRoomRequest request);

    BookedRoomRequest toBookedRoomRequest(BookRequest request);

    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "services", ignore = true)
    BookedRoomResponse toBookedRoomResponse(BookedRoom bookedRoom);
    @Mapping(target = "hotel", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "services", ignore = true)
    void updateBookedRoom(@MappingTarget BookedRoom bookedRoom, BookedRoomRequest request);
}
