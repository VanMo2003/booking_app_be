package com.example.booking_app.dto.request;

import com.example.booking_app.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "INVALID_USERNAME")
    String username;

    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;

    String fullName;
    String phone;
    String gender;

    @DobConstraint(message = "INVALID_DOB")
    LocalDate dateOfBirth;

    String birthPlace;
    String address;
    List<String> roles = List.of("USER");
    boolean active = true;
    Date onCreate = new Date();
    Date onUpdate = new Date();
}
