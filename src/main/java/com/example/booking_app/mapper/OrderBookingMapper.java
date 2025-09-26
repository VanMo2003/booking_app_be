package com.example.booking_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.booking_app.dto.request.OrderBookingRequest;
import com.example.booking_app.dto.response.OrderBookingResponse;
import com.example.booking_app.entity.Booking;

@Mapper(componentModel = "spring")
public interface OrderBookingMapper {
    @Mapping(target = "user", ignore = true)
    Booking toOrderBooking(OrderBookingRequest request);

    @Mapping(target = "user", ignore = true)
    OrderBookingResponse toOrderBookingResponse(Booking booking);

    @Mapping(target = "user", ignore = true)
    void updateOrderBooking(@MappingTarget Booking footballPitches, OrderBookingRequest request);
}
