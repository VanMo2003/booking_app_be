package com.example.booking_app.repository;

import com.example.booking_app.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {
    boolean existsByUserId(String userId);

    List<Hotel> findByNameHotel(String nameHotel);

    List<Hotel> findByAddress(String address);
}
