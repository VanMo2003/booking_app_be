package com.example.booking_app.dto.request;

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
    double price;
    int numberOfRoom;
    String userID;
}
