package com.example.booking_app.dto.request;

import com.example.booking_app.constant.PaymentMethod;
import com.example.booking_app.entity.Booking;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillRequest {
    Long bookingId;

    PaymentMethod paymentMethod = PaymentMethod.DIRECT_PAYMENT;
    LocalDate paymentDate = LocalDate.now();

    Date onCreate = new Date();
    Date onUpdate = new Date();
}
