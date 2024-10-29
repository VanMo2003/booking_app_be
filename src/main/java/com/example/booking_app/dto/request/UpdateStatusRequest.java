package com.example.booking_app.dto.request;


import com.example.booking_app.constant.StatusOrder;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateStatusRequest {
    StatusOrder statusOrder;
}
