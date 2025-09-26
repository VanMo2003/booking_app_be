package com.example.booking_app.dto.request;

import java.time.LocalDate;
import java.util.Date;

import com.example.booking_app.constant.PaymentMethod;
import com.example.booking_app.constant.StatusOrder;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
    LocalDate bookingDate = LocalDate.now();

    StatusOrder statusOrder = StatusOrder.PENDING;
    PaymentMethod paymentMethod;

    Long bookedRoomId;
    double price;

    Date onCreate = new Date();
    Date onUpdate = new Date();
}
