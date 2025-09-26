package com.example.booking_app.dto.response;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.example.booking_app.entity.Hotel;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookedRoomResponse {
    Long id;
    LocalDate arrivalDate;
    LocalDate departureDate;
    List<RoomResponse> rooms;
    List<ServiceResponse> services;
    boolean isCheckedIn = false;
    boolean isCheckedOut = false;
    String note;
    Hotel hotel;
    Date onCreate;
    Date onUpdate;
}
