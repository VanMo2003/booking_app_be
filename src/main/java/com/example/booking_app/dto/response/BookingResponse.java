package com.example.booking_app.dto.response;

import com.example.booking_app.constant.PaymentMethod;
import com.example.booking_app.constant.StatusOrder;
import com.example.booking_app.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
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
public class BookingResponse {
    Long id;

    LocalDate bookingDate;
    StatusOrder statusOrder;
    PaymentMethod paymentMethod;

    User user;
    BookedRoomResponse bookedRoom;

    Date onCreate;
    Date onUpdate;
}
