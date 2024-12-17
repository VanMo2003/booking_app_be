package com.example.booking_app.dto.request;

import com.example.booking_app.entity.Hotel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomRequest {
    String name;
    double price;
    String description;
    Long hotelId;
}
