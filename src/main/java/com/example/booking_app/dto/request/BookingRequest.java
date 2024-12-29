package com.example.booking_app.dto.request;

import com.example.booking_app.constant.PaymentMethod;
import com.example.booking_app.constant.StatusOrder;
import com.example.booking_app.entity.BookedRoom;
import com.example.booking_app.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

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
    double price = 0.0;

    Date onCreate = new Date();
    Date onUpdate = new Date();
}
