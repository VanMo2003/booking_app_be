package com.example.booking_app.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelReviewResponse {

    double rating;
    String reviewText;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    UserResponse user;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    HotelResponse hotel;

    LocalDate reviewDate;
}
