package com.example.booking_app.dto.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import com.example.booking_app.constant.StatusOrder;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderBookingResponse {
    int numberOfRoom;
    Locale dateCheckIn;
    Locale dateCheckOut;
    BigDecimal totalPrice;

    StatusOrder statusOrder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    UserResponse user;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    HotelResponse hotel;

    Date onCreate;
    Date onUpdate;
}
