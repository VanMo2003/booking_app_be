package com.example.booking_app.repository;

import com.example.booking_app.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OrderBookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(String userId);
    ArrayList<Booking> findByHotelId(Long hotelId);

}
