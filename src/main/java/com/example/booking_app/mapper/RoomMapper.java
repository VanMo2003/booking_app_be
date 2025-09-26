package com.example.booking_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.booking_app.dto.request.RoomRequest;
import com.example.booking_app.dto.response.RoomResponse;
import com.example.booking_app.entity.Room;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "hotel", ignore = true)
    Room toRoom(RoomRequest request);

    @Mapping(target = "hotel", ignore = true)
    RoomResponse toRoomResponse(Room user);

    @Mapping(target = "hotel", ignore = true)
    void updateRoom(@MappingTarget Room user, RoomRequest request);
}
