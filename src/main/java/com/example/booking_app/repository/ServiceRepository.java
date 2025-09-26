package com.example.booking_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findAllByHotel(Hotel hotel);
}
