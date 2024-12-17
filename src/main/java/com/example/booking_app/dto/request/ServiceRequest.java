package com.example.booking_app.dto.request;

import com.example.booking_app.entity.Hotel;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceRequest {
    String name;
    double price;
    String description;
    Long hotelId;
}
