package com.example.booking_app.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import com.example.booking_app.constant.PaymentMethod;
import jakarta.persistence.*;

import com.example.booking_app.constant.StatusOrder;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    int numberOfRoom;
    LocalDate bookingDate;

    @Enumerated(EnumType.STRING)
    StatusOrder statusOrder;

    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    Hotel hotel;

    @OneToOne
    BookedRoom bookedRoom;

    Date onCreate;
    Date onUpdate;
}
