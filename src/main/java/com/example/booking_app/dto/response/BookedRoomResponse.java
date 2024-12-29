package com.example.booking_app.dto.response;

import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.Room;
import com.example.booking_app.entity.Service;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
    double price;
    boolean isCheckedIn = false;
    boolean isCheckedOut = false;
    String note;
    Hotel hotel;
    Date onCreate;
    Date onUpdate;
}
