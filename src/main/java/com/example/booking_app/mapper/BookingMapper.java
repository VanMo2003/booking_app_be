package com.example.booking_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.booking_app.dto.request.BookingRequest;
import com.example.booking_app.dto.request.OrderRequest;
import com.example.booking_app.dto.response.BookingResponse;
import com.example.booking_app.entity.Booking;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "bookedRoom", ignore = true)
    Booking toBooking(BookingRequest request);

    BookingRequest toBookingRequest(OrderRequest request);

    @Mapping(target = "bookedRoom", ignore = true)
    BookingResponse toBookingResponse(Booking booking);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "bookedRoom", ignore = true)
    void updateBooking(@MappingTarget Booking booking, BookingRequest request);
}
