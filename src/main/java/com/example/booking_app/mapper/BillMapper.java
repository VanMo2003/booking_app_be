package com.example.booking_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.booking_app.dto.request.BillRequest;
import com.example.booking_app.dto.response.BillResponse;
import com.example.booking_app.entity.Bill;

@Mapper(componentModel = "spring")
public interface BillMapper {
    @Mapping(target = "booking", ignore = true)
    Bill toBill(BillRequest request);

    @Mapping(target = "booking", ignore = true)
    BillResponse toBillResponse(Bill bill);

    @Mapping(target = "booking", ignore = true)
    void updateBill(@MappingTarget Bill bill, BillRequest request);
}
