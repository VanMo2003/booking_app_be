package com.example.booking_app.dto.request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

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
    Locale dateCheckIn;
    Locale dateCheckOut;
    BigDecimal totalPrice;
    String userId;
    Long hotelId;

    StatusOrder statusOrder = StatusOrder.PENDING;
    Date onCreate = new Date();
    Date onUpdate = new Date();
}
