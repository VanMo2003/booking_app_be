package com.example.booking_app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDate arrivalDate;
    LocalDate departureDate;
    @ManyToMany
    List<Room> rooms;

    @ManyToMany
    List<Service> services;
    boolean isCheckedIn = false;
    boolean isCheckedOut = false;
    String note;

    @ManyToOne
    Hotel hotel;

    Date onCreate;
    Date onUpdate;
}
