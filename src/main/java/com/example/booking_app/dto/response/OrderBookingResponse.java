package com.example.booking_app.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import com.example.booking_app.constant.PaymentMethod;
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
    Long id;
    int numberOfRoom;
    LocalDate dateCheckIn;
    LocalDate dateCheckOut;
    BigDecimal totalPrice;

    StatusOrder statusOrder;
    PaymentMethod paymentMethod;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    UserResponse user;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    HotelResponse hotel;

    Date onCreate;
    Date onUpdate;
}
