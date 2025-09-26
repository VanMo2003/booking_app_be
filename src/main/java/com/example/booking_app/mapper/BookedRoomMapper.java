package com.example.booking_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.booking_app.dto.request.BookedRoomRequest;
import com.example.booking_app.dto.request.OrderRequest;
import com.example.booking_app.dto.response.BookedRoomResponse;
import com.example.booking_app.entity.BookedRoom;

@Mapper(componentModel = "spring")
public interface BookedRoomMapper {
    @Mapping(target = "hotel", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "services", ignore = true)
    BookedRoom toBookedRoom(BookedRoomRequest request);

    BookedRoomRequest toBookedRoomRequest(OrderRequest request);

    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "services", ignore = true)
    BookedRoomResponse toBookedRoomResponse(BookedRoom bookedRoom);

    @Mapping(target = "hotel", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "services", ignore = true)
    void updateBookedRoom(@MappingTarget BookedRoom bookedRoom, BookedRoomRequest request);
}
