package com.example.booking_app.repository;

import com.example.booking_app.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    boolean existsByUserId(String userId);
}
