package com.example.booking_app.dto.request;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.example.booking_app.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String fullName;
    String phone;
    String gender;

    @DobConstraint(message = "INVALID_DOB")
    LocalDate dateOfBirth;

    String birthPlace;
    String address;
    List<String> roles;
    boolean active = true;
    Date onUpdate = new Date();
}
