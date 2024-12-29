package com.example.booking_app.dto.response;

import com.example.booking_app.entity.Hotel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticalResponse {
    Long id;
    LocalDate revenueMonth;
    double totalRevenue;
    HotelResponse hotel;
    Date onCreate;
}
