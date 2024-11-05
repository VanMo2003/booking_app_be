package com.example.booking_app.repository;

import com.example.booking_app.dto.response.OrderBookingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.booking_app.entity.OrderBooking;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OrderBookingRepository extends JpaRepository<OrderBooking, Long> {
    List<OrderBooking> findByUserId(String userId);
    ArrayList<OrderBooking> findByHotelId(Long hotelId);

}
