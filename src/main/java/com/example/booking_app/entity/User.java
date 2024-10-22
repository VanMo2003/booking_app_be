package com.example.booking_app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String username;
    String password;
    String fullName;
    String phone;
    String gender;
    LocalDate dateOfBirth;
    String birthPlace;
    String address;
    boolean active;
    Date onCreate;
    Date onUpdate;
}