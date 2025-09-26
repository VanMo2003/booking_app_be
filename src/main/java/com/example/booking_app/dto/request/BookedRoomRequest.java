package com.example.booking_app.dto.request;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookedRoomRequest {
    LocalDate arrivalDate;
    LocalDate departureDate;
    Set<Long> rooms;
    Set<Long> services = new HashSet<>();
    String note;
    Long hotelId;
    boolean isCheckedIn = false;
    boolean isCheckedOut = false;
    Date onCreate = new Date();
    Date onUpdate = new Date();
}
