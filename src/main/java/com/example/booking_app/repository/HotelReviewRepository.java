package com.example.booking_app.repository;


import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.HotelReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelReviewRepository extends JpaRepository<HotelReview, Long> {
    List<HotelReview> findByHotel(Hotel hotel);
}
