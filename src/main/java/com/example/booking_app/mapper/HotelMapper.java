package com.example.booking_app.mapper;

import com.example.booking_app.dto.request.HotelRequest;
import com.example.booking_app.dto.response.HotelResponse;
import com.example.booking_app.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    @Mapping(target = "user", ignore = true)
    Hotel toHotel(HotelRequest request);

    HotelResponse toHotelResponse(Hotel footballPitches);

    @Mapping(target = "user", ignore = true)
    void updateHotel(@MappingTarget Hotel footballPitches, HotelRequest request);
}
