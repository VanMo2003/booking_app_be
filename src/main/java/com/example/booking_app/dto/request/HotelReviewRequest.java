package com.example.booking_app.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelReviewRequest {
    double rating;
    String reviewText;
    Long hotelId;
    Date reviewDate = new Date();
}
