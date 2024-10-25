package com.example.booking_app.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String pathImage;
    String nameHotel;
    String address;
    String description;
    double price;
    int numberOfRoom;

    @OneToOne
    User user;
}
