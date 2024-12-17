package com.example.booking_app.mapper;


import com.example.booking_app.dto.request.RoomRequest;
import com.example.booking_app.dto.request.ServiceRequest;
import com.example.booking_app.dto.response.RoomResponse;
import com.example.booking_app.dto.response.ServiceResponse;
import com.example.booking_app.entity.Room;
import com.example.booking_app.entity.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "hotel", ignore = true)
    Room toRoom(RoomRequest request);

    RoomResponse toRoomResponse(Room user);
    @Mapping(target = "hotel", ignore = true)
    void updateRoom(@MappingTarget Room user, RoomRequest request);
}
