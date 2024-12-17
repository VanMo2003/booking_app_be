package com.example.booking_app.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String pathImage;
    String nameHotel;
    String address;
    String description;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    boolean active;
    Date onCreate;
    Date onUpdate;
}
