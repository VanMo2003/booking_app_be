package com.example.booking_app.dto.response;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String fullName;
    String phone;
    String gender;
    LocalDate dateOfBirth;
    String birthPlace;
    String address;
    Set<RoleResponse> roles;
    boolean active;
    Date onCreate;
    Date onUpdate;
}
