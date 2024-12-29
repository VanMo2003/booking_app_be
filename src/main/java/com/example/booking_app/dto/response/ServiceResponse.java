package com.example.booking_app.dto.response;

import com.example.booking_app.entity.Hotel;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceResponse {
    Long id;
    String name;
    double price;
    String description;
    HotelResponse hotel;
}
