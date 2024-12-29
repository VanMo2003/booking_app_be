package com.example.booking_app.dto.response;

import com.example.booking_app.constant.PaymentMethod;
import com.example.booking_app.entity.Booking;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillResponse {
    Long id;

    BookingResponse booking;

    PaymentMethod paymentMethod;
    LocalDate paymentDate;

    Date onCreate;
    Date onUpdate;
}
