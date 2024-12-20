package com.example.booking_app.dto.request;


import com.example.booking_app.constant.PaymentMethod;
import com.example.booking_app.constant.StatusOrder;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequest {
    LocalDate bookingDate = LocalDate.now();

    StatusOrder statusOrder = StatusOrder.PENDING;
    PaymentMethod paymentMethod;

    String userId;

    LocalDate arrivalDate;
    LocalDate departureDate;
    Set<Long> rooms;
    Set<Long> services;
    String note;
    Long hotelId;
    double price = 0.0;
    boolean isCheckedIn = false;
    boolean isCheckedOut = false;

    Date onCreate = new Date();
    Date onUpdate = new Date();
}
