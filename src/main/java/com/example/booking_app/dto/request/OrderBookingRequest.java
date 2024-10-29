package com.example.booking_app.dto.request;

import java.math.BigDecimal;
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
public class OrderBookingRequest {
    int numberOfRoom;
    LocalDate dateCheckIn;
    LocalDate dateCheckOut;
    String userId;
    Long hotelId;

    StatusOrder statusOrder = StatusOrder.PENDING;
    PaymentMethod paymentMethod = PaymentMethod.DIRECT_PAYMENT;
    Date onCreate = new Date();
    Date onUpdate = new Date();
}
