package com.example.booking_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.booking_app.dto.request.HotelReviewRequest;
import com.example.booking_app.dto.response.HotelReviewResponse;
import com.example.booking_app.entity.HotelReview;

@Mapper(componentModel = "spring")
public interface HotelReviewMapper {
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    HotelReview toHotelReview(HotelReviewRequest request);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    HotelReviewResponse toHotelReviewResponse(HotelReview hotelReview);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    void updateHotel(@MappingTarget HotelReview hotelReview, HotelReviewRequest request);
}
