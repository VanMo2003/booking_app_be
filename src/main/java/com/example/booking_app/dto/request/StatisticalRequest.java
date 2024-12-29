package com.example.booking_app.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticalRequest {
    LocalDate revenueMonth = LocalDate.now();
    double totalRevenue = 0.0;

    Date onCreate = new Date();
}
