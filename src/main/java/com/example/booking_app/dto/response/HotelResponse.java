package com.example.booking_app.dto.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelResponse {
    String pathImage;
    String nameHotel;
    String address;
    String description;
    double price;
    int numberOfRoom;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    UserResponse user;

    boolean active;
    Date onCreate;
    Date onUpdate;
}
