package com.example.booking_app.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelReviewRequest {
    double rating;
    String reviewText;
    String userId;
    Long hotelId;
    LocalDate reviewDate = LocalDate.now();
}
