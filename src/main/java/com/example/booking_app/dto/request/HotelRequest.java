package com.example.booking_app.dto.request;

import java.util.Date;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelRequest {
    String pathImage;
    String nameHotel;
    String address;
    String description;
    String userID;
    double rating;
    boolean active = true;
    Date onCreate = new Date();
    Date onUpdate = new Date();
}
