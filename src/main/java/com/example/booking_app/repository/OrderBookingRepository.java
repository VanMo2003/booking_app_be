package com.example.booking_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.booking_app.entity.Booking;

@Repository
public interface OrderBookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(String userId);
    //    ArrayList<Booking> findByHotelId(Long hotelId);

}
