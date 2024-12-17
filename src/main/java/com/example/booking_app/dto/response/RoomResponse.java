package com.example.booking_app.dto.response;

import com.example.booking_app.entity.Hotel;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomResponse {
    Long id;
    String name;
    double price;
    String description;
    Hotel hotel;
}
