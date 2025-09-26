package com.example.booking_app.dto.request;

import java.time.LocalDate;
import java.util.Date;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
