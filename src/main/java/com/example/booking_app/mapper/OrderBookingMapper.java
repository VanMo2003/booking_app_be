package com.example.booking_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.booking_app.dto.request.OrderBookingRequest;
import com.example.booking_app.dto.response.OrderBookingResponse;
import com.example.booking_app.entity.OrderBooking;

@Mapper(componentModel = "spring")
public interface OrderBookingMapper {
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    OrderBooking toOrderBooking(OrderBookingRequest request);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    OrderBookingResponse toOrderBookingResponse(OrderBooking orderBooking);

    @Mapping(target = "user", ignore = true)
    void updateOrderBooking(@MappingTarget OrderBooking footballPitches, OrderBookingRequest request);
}
